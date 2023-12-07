package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.enums.AccommodationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccommodationService implements ServiceInterface<Accommodation>{
    @Autowired
    private IAccommodationRepository repository;

    @Override
    public Collection<Accommodation> getAll() {
        return repository.findAll();
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
    public void delete(Long id) {
        repository.deleteById(id);
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

}
