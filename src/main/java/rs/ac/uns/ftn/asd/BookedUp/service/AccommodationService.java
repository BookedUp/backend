package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.AccommodationStatus;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.AccommodationType;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.Amenity;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationRepository;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReservationRepository;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReviewRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    public List<Accommodation> findAllByHostId(Long id){
        return repository.findAllByHostId(id);
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
        return repository.searchAccommodations(location, guestsNumber, startDate, endDate);
    }

    public double calculateTotalPrice(Accommodation accommodation, Date startDate, Integer daysNumber, Integer guestsNumber){
        if (!accommodation.getPriceChanges().isEmpty()) {

            PriceChange selectedChangeDate = null;
            for (PriceChange priceChange : accommodation.getPriceChanges()) {
                if (priceChange.getChangeDate().before(startDate) || priceChange.getChangeDate().equals(startDate)) {
                    if (selectedChangeDate == null || selectedChangeDate.getChangeDate().before(priceChange.getChangeDate())) {
                        selectedChangeDate = priceChange;
                    }
                }
            }

            if (selectedChangeDate != null) {
                return (selectedChangeDate.getNewPrice() * daysNumber * guestsNumber);
            }
        }
//        System.out.println("Total price: " + accommodation.getPrice() * num);
        return (accommodation.getPrice() * daysNumber * guestsNumber);
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


    public List<Accommodation> filterAccommodations(List<Amenity> amenities, AccommodationType accommodationType, Double minPrice, Double maxPrice) {
        List<Accommodation> filteredAccommodations = repository.filterAccommodations(amenities, accommodationType, minPrice, maxPrice);
        return filteredAccommodations;
//        for (Accommodation accommodation : filteredAccommodations){
//            System.out.println(accommodation.getId());
//            System.out.println(accommodation.getType());
//            System.out.println(accommodation.getPrice());
//            for (Amenity amenity : accommodation.getAmenities()){
//                System.out.println(amenity);
//            }
//        }
//        if (amenities != null && !amenities.isEmpty()) {
//            filteredAccommodations = filteredAccommodations.stream()
//                    .filter(accommodation -> accommodation.getAmenities().containsAll(amenities))
//                    .collect(Collectors.toList());
//        }
//        return filteredAccommodations;
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



}

