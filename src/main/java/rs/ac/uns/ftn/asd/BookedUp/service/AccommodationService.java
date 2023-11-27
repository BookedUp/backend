package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.repository.AccommodationRepository;
import rs.ac.uns.ftn.asd.BookedUp.repository.UserRepository;

import java.util.Collection;

@Service
public class AccommodationService implements IAcommodationService{
    @Autowired
    private AccommodationRepository accommodationRepository;
    @Override
    public Collection<Accommodation> getAll() {
        Collection<Accommodation> accommodations = accommodationRepository.getAll();
        return accommodations;
    }

    @Override
    public Accommodation getById(Long id) {
        Accommodation accommodation = accommodationRepository.getById(id);
        return accommodation;
    }

    @Override
    public Accommodation create(Accommodation accommodation) throws Exception {
        if (accommodation.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Accommodation savedAccommodation = accommodationRepository.create(accommodation);
        return savedAccommodation;
    }

    @Override
    public Accommodation update(Accommodation accommodation) throws Exception {
        System.out.println(accommodation.getId() + "SEREMISE");
        Accommodation accommodationToUpdate = getById(accommodation.getId());
        if (accommodationToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        accommodationToUpdate.setName(accommodation.getName());
        accommodationToUpdate.setDescription(accommodation.getDescription());
        accommodationToUpdate.setAddress(accommodation.getAddress());
        accommodationToUpdate.setAmenities(accommodation.getAmenities());
        accommodationToUpdate.setPhotos(accommodation.getPhotos());
        accommodationToUpdate.setMinGuests(accommodation.getMinGuests());
        accommodationToUpdate.setMaxGuests(accommodation.getMaxGuests());
        accommodationToUpdate.setType(accommodation.getType());
        accommodationToUpdate.setPriceType(accommodation.getPriceType());
        accommodationToUpdate.setAvailability(accommodation.getAvailability());
        accommodationToUpdate.setPrice(accommodation.getPrice());
        accommodationToUpdate.setStatus(accommodation.getStatus());
        accommodationToUpdate.setCancellationDeadline(accommodation.getCancellationDeadline());
        accommodationToUpdate.setAutomaticReservationAcceptance(accommodation.isAutomaticReservationAcceptance());
        accommodationToUpdate.setReservations(accommodation.getReservations());
        accommodationToUpdate.setReviews(accommodation.getReviews());
        accommodationToUpdate.setAverageRating(accommodation.getAverageRating());
        accommodationToUpdate.setPriceChanges(accommodation.getPriceChanges());

        Accommodation updatedAcommodation = accommodationRepository.create(accommodationToUpdate);
        return updatedAcommodation;
    }

    @Override
    public void delete(Long id) {
        accommodationRepository.delete(id);
    }
}
