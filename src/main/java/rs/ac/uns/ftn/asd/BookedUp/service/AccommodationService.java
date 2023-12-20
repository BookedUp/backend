package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.AccommodationStatus;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.AccommodationType;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.Amenity;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.PhotoDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.PriceChangeDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AddressMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.DateRangeMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.PhotoMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.PriceChangeMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationRepository;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReservationRepository;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReviewRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AccommodationService implements ServiceInterface<Accommodation>{
    @Autowired
    private IAccommodationRepository repository;

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private IReviewRepository reviewRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReviewService reviewService;

    @Override
    public Collection<Accommodation> getAll() {
        List<Accommodation> accommodations = repository.findAll();
        for (Accommodation accommodation: accommodations){
            updatePrice(accommodation);
        }
        return accommodations;
    }

    @Override
    public Accommodation getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Accommodation create(Accommodation accommodation) throws Exception {
        if (accommodation.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Calendar calendar = Calendar.getInstance();

        for(DateRange dr: accommodation.getAvailability()){
            calendar.setTime(dr.getStartDate());
            calendar.set(Calendar.HOUR_OF_DAY, 13);
            calendar.setTime(dr.getEndDate());
            calendar.set(Calendar.HOUR_OF_DAY, 13);
        }
        return repository.save(accommodation);
    }

    @Override
    public Accommodation save(Accommodation accommodation) throws Exception {
        return repository.save(accommodation);
    }
//
//    @Override
//    public AccommodationDTO update(AccommodationDTO accommodationDto) throws Exception {
//        Accommodation accommodation = accommodationMapper.toEntity(accommodationDto);
//        Accommodation accommodationToUpdate = repository.findById(accommodation.getId()).orElse(null);
//        if (accommodationToUpdate == null) {
//            throw new Exception("Trazeni entitet nije pronadjen.");
//        }
//        //accommodationToUpdate.setHost(accommodation.getHost());
//        accommodationToUpdate.setName(accommodation.getName());
//        accommodationToUpdate.setDescription(accommodation.getDescription());
//        accommodationToUpdate.setAddress(accommodation.getAddress());
//        accommodationToUpdate.setAmenities(accommodation.getAmenities());
//        accommodationToUpdate.setPhotos(accommodation.getPhotos());
//        accommodationToUpdate.setMinGuests(accommodation.getMinGuests());
//        accommodationToUpdate.setMaxGuests(accommodation.getMaxGuests());
//        accommodationToUpdate.setType(accommodation.getType());
//        accommodationToUpdate.setPriceType(accommodation.getPriceType());
//        accommodationToUpdate.setAvailability(accommodation.getAvailability());
//        accommodationToUpdate.setPrice(accommodation.getPrice());
//        accommodationToUpdate.setStatus(accommodation.getStatus());
//        accommodationToUpdate.setCancellationDeadline(accommodation.getCancellationDeadline());
//        accommodationToUpdate.setAutomaticReservationAcceptance(accommodation.isAutomaticReservationAcceptance());
//        accommodationToUpdate.setReservations(accommodation.getReservations());
//        accommodationToUpdate.setReviews(accommodation.getReviews());
//        accommodationToUpdate.setAverageRating(accommodation.getAverageRating());
//        accommodationToUpdate.setPriceChanges(accommodation.getPriceChanges());
//
//        Accommodation updatedAcommodation = repository.save(accommodationToUpdate);
//        return accommodationMapper.toDto(updatedAcommodation);
//    }

    @Override
    public void delete(Long id) throws Exception {

        Accommodation accommodation = repository.findById(id).orElse(null);

        if (accommodation == null)
            throw new Exception("Accommodation doesn't exist");

        if (hasActiveReservations(accommodation.getId())) {
            throw new Exception("Guest has active reservations and cannot be deleted");
        }

        Address address = accommodation.getAddress();
        if(address != null){
            address.setActive(false);
        }

        List<Reservation> reservations = reservationService.findAllByAccommodationId(accommodation.getId());
        if(!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                reservation.setActive(false);
                reservationRepository.save(reservation);
            }
        }

        List<Photo> photos = accommodation.getPhotos();
        if(!photos.isEmpty()) {
            photos.clear();
            accommodation.setPhotos(photos);
        }

        List<PriceChange> priceChanges = accommodation.getPriceChanges();
        if(!priceChanges.isEmpty()) {
            priceChanges.clear();
            accommodation.setPriceChanges(priceChanges);
        }

        List<DateRange> availability = accommodation.getAvailability();
        if(!availability.isEmpty()) {
            availability.clear();
            accommodation.setAvailability(availability);
        }

        List<Review> reviews = reviewService.findAllByAccommodationId(accommodation.getId());
        if(!reviews.isEmpty()) {
            for (Review review : reviews) {
                review.setIsReviewActive(false);
                reviewRepository.save(review);
            }
        }

        List<Amenity> amenities = accommodation.getAmenities();
        if(!amenities.isEmpty()) {
            amenities.clear();
            accommodation.setAmenities(amenities);
        }

        accommodation.setActive(false);
        repository.save(accommodation);
    }

    public Accommodation approve(Accommodation accommodation) throws Exception {
        if (accommodation == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        Accommodation accommodationToUpdate = repository.findById(accommodation.getId()).orElse(null);
        assert accommodationToUpdate != null;
        accommodationToUpdate.setStatus(AccommodationStatus.ACTIVE);
        return repository.save(accommodationToUpdate);
    }

    public Accommodation reject(Accommodation accommodation) throws  Exception{
        if (accommodation == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        Accommodation accommodationToUpdate = repository.findById(accommodation.getId()).orElse(null);
        assert accommodationToUpdate != null;
        accommodationToUpdate.setStatus(AccommodationStatus.REJECTED);
        return repository.save(accommodationToUpdate);
    }

    public List<Accommodation> findAllActiveByHostId(Long id){
        return repository.findAllActiveByHostId(id);
    }

    public List<Accommodation> findAllRejectedByHostId(Long id){
        return repository.findAllRejectedByHostId(id);
    }

    public List<Accommodation> findAllRequestsByHostId(Long id){
        return repository.findAllRequestsByHostId(id);
    }

    public List<Accommodation> findAllChanged(){
        return repository.findAllChanged();
    }

    public List<Accommodation> findAllCreated(){
        return repository.findAllCreated();
    }

    public List<Accommodation> findAllModified(){
        return repository.findAllModified();
    }

    public boolean hasActiveReservations(Long id) {
        List<Reservation> reservations = reservationService.findAllByAccommodationId(id);
        if ( reservations!= null) {
            return reservations.stream()
                    .anyMatch(reservation -> reservation.getStatus() != ReservationStatus.CANCELLED
                            && reservation.getStatus() != ReservationStatus.COMPLETED
                            && reservation.getStatus() != ReservationStatus.REJECTED);
        }
        return false;
    }

    public List<Accommodation> searchAccommodations(String location, Integer guestsNumber, Date startDate, Date endDate){
        List<Accommodation> accommodations = repository.findAll();
        boolean isStartDateAndEndToday = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(LocalDate.now()) && endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isEqual(LocalDate.now());
        List<Accommodation> filteredAccommodations = new ArrayList<Accommodation>();
        if (isStartDateAndEndToday){
            System.out.println("danas");
            filteredAccommodations = filterByToday(accommodations, location, guestsNumber, startDate, endDate);
        } else{
            filteredAccommodations = createDynamicFilter(accommodations, location, guestsNumber, startDate, endDate);
        }

        filteredAccommodations.forEach(accommodation ->
                System.out.println("IDDDDDDDDDDD: " + accommodation.getId()));
        return filteredAccommodations;
    }


    private static List<Accommodation> createDynamicFilter(List<Accommodation> accommodations, String location, Integer guestsNumber, Date startDate, Date endDate) {
        return accommodations.stream()
                .filter(accommodation ->
                        (location.isEmpty() || accommodation.getAddress().getCity().equals(location) || accommodation.getAddress().getCountry().equals(location)) &&
                                (guestsNumber == null || guestsNumber == 0 || (accommodation.getMinGuests() <= guestsNumber && guestsNumber <= accommodation.getMaxGuests())) &&
                                (isContinuousAvailability(accommodation.getAvailability(), startDate, endDate) ||
                                (startDate == null || endDate == null || accommodation.getAvailability().stream().anyMatch(date ->
                                        startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(date.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) >= 0 &&
                                                startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(date.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) < 0 &&
                                                endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(date.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) <= 0 &&
                                                endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(date.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) > 0))))

                        .collect(Collectors.toList());
    }



    private static boolean isContinuousAvailability(List<DateRange> availability, Date startDate, Date endDate) {
        List<LocalDate> requestedDates = new ArrayList<>();
        LocalDate currentDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        while (!currentDate.isAfter(endLocalDate)) {
            requestedDates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        List<Date> allDates = new ArrayList<Date>();
        for (DateRange dr : availability){
            LocalDate start= dr.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate end = dr.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            while (!start.isAfter(end)) {
                allDates.add(Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                start = start.plusDays(1);
            }
        }
        boolean allDatesMatch = true;
        for (LocalDate localDate : requestedDates) {
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (!allDates.contains(date)) {
                allDatesMatch = false;
            }
        }

        if (allDatesMatch) {
            return true;
        } else {
            return false;
        }
    }


    private static List<Accommodation> filterByToday(List<Accommodation> accommodations, String location, Integer guestsNumber, Date startDate, Date endDate){
        return accommodations.stream()
                .filter(accommodation ->
                        (location.isEmpty() || accommodation.getAddress().getCity().equals(location) || accommodation.getAddress().getCountry().equals(location)) &&
                                (guestsNumber == null || guestsNumber == 0 || (accommodation.getMinGuests() <= guestsNumber && guestsNumber <= accommodation.getMaxGuests())) &&
                                (endDate == null || accommodation.getAvailability().stream().anyMatch(date -> endDate.toInstant().compareTo(date.getEndDate().toInstant()) <= 0)))
                .collect(Collectors.toList());
    }

    public double calculateTotalPrice(Accommodation accommodation, Date startDate, Date endDate, Integer daysNumber, Integer guestsNumber) {
        double total = 0.0;
        if (!accommodation.getPriceChanges().isEmpty()) {
            List<PriceChange> datesBetweenSorted = new ArrayList<>();
            PriceChange closestDateBeforeStartDate = null;

            for (PriceChange pr : accommodation.getPriceChanges()) {
                Date prDate = pr.getChangeDate();

                if (prDate.before(startDate)) {
                    if (closestDateBeforeStartDate == null || prDate.after(closestDateBeforeStartDate.getChangeDate())) {
                        closestDateBeforeStartDate = pr;
                    }
                }
            }
            if (closestDateBeforeStartDate != null) {
                datesBetweenSorted.add(new PriceChange(closestDateBeforeStartDate.getId(), startDate, closestDateBeforeStartDate.getNewPrice()));
            } else {
                datesBetweenSorted.add(new PriceChange(startDate, accommodation.getPrice()));
            }
            datesBetweenSorted.add(new PriceChange(endDate, 0.0));
            datesBetweenSorted.addAll(
                    accommodation.getPriceChanges().stream()
                            .filter(date -> (date.getChangeDate().equals(startDate) || date.getChangeDate().equals(endDate) || (date.getChangeDate().after(startDate)  && date.getChangeDate().before(endDate))))
                            .collect(Collectors.toList())
            );

            Collections.sort(datesBetweenSorted, Comparator.comparing(PriceChange::getChangeDate));
            List<Double> calculatedPrices = new ArrayList<Double>();
            for (int i = 1; i < datesBetweenSorted.size(); i++) {
                PriceChange currentPriceChange = datesBetweenSorted.get(i);
                PriceChange previousPriceChange = datesBetweenSorted.get(i - 1);

                LocalDate currentLocalDate = currentPriceChange.getChangeDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                LocalDate previousLocalDate = previousPriceChange.getChangeDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

                long daysDifference = ChronoUnit.DAYS.between(previousLocalDate, currentLocalDate);
                double calculatedPrice = 0;
                if ((i - 1) == 0 && datesBetweenSorted.get(0).getChangeDate() == startDate) {
                    calculatedPrice = daysDifference * previousPriceChange.getNewPrice();
                } else {
                    calculatedPrice = daysDifference * previousPriceChange.getNewPrice();
                }
                calculatedPrices.add(calculatedPrice);
            }
            for (Double price : calculatedPrices) {
                total += price;
            }
            total *= guestsNumber;
        } else {
            total = accommodation.getPrice() * daysNumber * guestsNumber;
        }
        return  total;
    }

    public void updatePrice(Accommodation accommodation){
            Date today = new Date();
            if (!accommodation.getPriceChanges().isEmpty()) {

                PriceChange selectedChangeDate = null;
                for (PriceChange priceChange : accommodation.getPriceChanges()) {
                    if (priceChange.getChangeDate().before(today) || priceChange.getChangeDate().equals(today)) {
                        if (selectedChangeDate == null || selectedChangeDate.getChangeDate().before(priceChange.getChangeDate())) {
                            selectedChangeDate = priceChange;
                        }
                    }
                }

                if (selectedChangeDate != null) {
                    accommodation.setPrice(selectedChangeDate.getNewPrice());
                    repository.save(accommodation);
                }
            }
        }

    public List<Accommodation> filterAccommodationsByType(AccommodationType type){
       return repository.filterAccommodationsByType(type);
    }

    public List<Accommodation> findMostPopular() {
        List<Object[]> result = repository.findMostPopular();
        List<Accommodation> accommodations = new ArrayList<>();

        int count = 0;

        for (Object[] row : result) {
            if (count < 4) {
                Accommodation accommodation = (Accommodation) row[0];
                accommodations.add(accommodation);
                count++;
            } else {
                break;
            }
        }

        return accommodations;
    }


    public void approveAccommodation(Accommodation accommodation) {
        accommodation.setStatus(AccommodationStatus.ACTIVE);
        repository.save(accommodation);
    }

    public void rejectAccommodation(Accommodation accommodation) {
        accommodation.setStatus(AccommodationStatus.REJECTED);
        repository.save(accommodation);
    }

    public void updateAccommodation(Accommodation accommodationForUpdate, AccommodationDTO accommodationDTO) {
        accommodationForUpdate.setName(accommodationDTO.getName());
        accommodationForUpdate.setDescription(accommodationDTO.getDescription());
        accommodationForUpdate.setAddress(AddressMapper.toEntity(accommodationDTO.getAddress()));
        accommodationForUpdate.setAmenities(accommodationDTO.getAmenities());
        List<Photo> photos = new ArrayList<Photo>();
        if (accommodationDTO.getPhotos() != null){
            for (PhotoDTO photoDTO : accommodationDTO.getPhotos())
                photos.add(PhotoMapper.toEntity(photoDTO));
        }
        accommodationForUpdate.setPhotos(photos);
        accommodationForUpdate.setMinGuests(accommodationDTO.getMinGuests());
        accommodationForUpdate.setMaxGuests(accommodationDTO.getMaxGuests());
//        accommodationForUpdate.setType(accommodationDTO.getType()); ???
        List<DateRange> availability = accommodationDTO.getAvailability().stream()
                .map(DateRangeMapper::toEntity)
                .collect(Collectors.toList());

        accommodationForUpdate.setAvailability(availability);
        accommodationForUpdate.setPriceType(accommodationDTO.getPriceType());
        List<PriceChange> priceChanges = new ArrayList<PriceChange>();
        if (accommodationDTO.getPriceChanges() != null){
            for (PriceChangeDTO dto : accommodationDTO.getPriceChanges())
                priceChanges.add(PriceChangeMapper.toEntity(dto));
        }
        accommodationForUpdate.setPriceChanges(priceChanges);
        accommodationForUpdate.setAutomaticReservationAcceptance(accommodationDTO.isAutomaticReservationAcceptance());
        accommodationForUpdate.setPrice(accommodationDTO.getPrice());
        accommodationForUpdate.setCancellationDeadline(accommodationDTO.getCancellationDeadline());
        accommodationForUpdate.setStatus(AccommodationStatus.CHANGED);

        repository.save(accommodationForUpdate);

    }
}

