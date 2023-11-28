package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDetailedInDTO;
import rs.ac.uns.ftn.asd.BookedUp.repository.GuestRepository;

import java.util.Collection;

@Service
public class GuestService implements IGuestService{
    @Autowired
    private GuestRepository guestRepository;
    @Override
    public Collection<Guest> getAll() {
        Collection<Guest> guests = guestRepository.getAll();
        return guests;
    }

    @Override
    public Guest getById(Long id) {
        Guest guest = guestRepository.getById(id);
        return guest;
    }

    @Override
    public Guest create(Guest guest) throws Exception {
        if (guest.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        Guest savedGuest = guestRepository.create(guest);
        return savedGuest;
    }

    @Override
    public Guest update(Guest guest) throws Exception {
        Guest guestToUpdate = getById(guest.getId());
        if (guestToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        guestToUpdate.setFirstName(guest.getFirstName());
        guestToUpdate.setLastName(guest.getLastName());
        guestToUpdate.setAddress(guest.getAddress());
        guestToUpdate.setEmail(guest.getEmail());
        guestToUpdate.setPassword(guest.getPassword());
        guestToUpdate.setPhone(guest.getPhone());
        guestToUpdate.setRole(guest.getRole());
        guestToUpdate.setBlocked(guest.isBlocked());
        guestToUpdate.setRequests(guest.getRequests());
        guestToUpdate.setReservations(guest.getReservations());
        guestToUpdate.setFavourites(guest.getFavourites());
        guestToUpdate.setReviews(guest.getReviews());
        guestToUpdate.setNotifications(guest.getNotifications());

        Guest updatedGuest = guestRepository.create(guestToUpdate);
        return updatedGuest;
    }

    @Override
    public void delete(Long id) {
        guestRepository.delete(id);

    }

    public void updateGuestInformation(Guest user, UserDetailedInDTO userDTO) {
    }
}
