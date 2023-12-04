package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.dto.GuestDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.HostDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.GuestMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.GuestRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class GuestService implements IGuestService{
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GuestMapper guestMapper;
    @Override
    public Collection<GuestDTO> getAll() {
        Collection<Guest> guests = guestRepository.getAll();
        Collection<GuestDTO> guestDTOS = new ArrayList<>();

        for (Guest guest : guests) {
            GuestDTO guestDTO= guestMapper.toDto(guest);
            guestDTOS.add(guestDTO);
        }

        return guestDTOS;
    }

    @Override
    public GuestDTO getById(Long id) {
        Guest guest = guestRepository.getById(id);
        return guestMapper.toDto(guest);
    }

    @Override
    public GuestDTO create(GuestDTO guestDto) throws Exception {
        if (guestDto.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        Guest guest = guestMapper.toEntity(guestDto);
        Guest savedGuest = guestRepository.create(guest);
        return guestMapper.toDto(savedGuest);
    }

    @Override
    public GuestDTO update(GuestDTO guestDto) throws Exception {
        Guest guest = guestMapper.toEntity(guestDto);
        Guest guestToUpdate = guestRepository.getById(guest.getId());
        if (guestToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        guestToUpdate.setFirstName(guest.getFirstName());
        guestToUpdate.setLastName(guest.getLastName());
        guestToUpdate.setAddress(guest.getAddress());
        guestToUpdate.setEmail(guest.getEmail());
        guestToUpdate.setPassword(guest.getPassword());
        guestToUpdate.setPhone(guest.getPhone());
        //guestToUpdate.setRole(guest.getRole());
        guestToUpdate.setBlocked(guest.isBlocked());
        guestToUpdate.setRequests(guest.getRequests());
        guestToUpdate.setReservations(guest.getReservations());
        guestToUpdate.setFavourites(guest.getFavourites());
        guestToUpdate.setReviews(guest.getReviews());
        guestToUpdate.setNotifications(guest.getNotifications());
        guestToUpdate.setNotificatonEnable(guest.isNotificatonEnable());

        guestToUpdate.setAuthority(guest.getAuthority());
        guestToUpdate.setProfilePicture(guest.getProfilePicture());
        guestToUpdate.setVerified(guest.isVerified());
        guestToUpdate.setLastPasswordResetDate(guest.getLastPasswordResetDate());

        Guest updatedGuest = guestRepository.create(guestToUpdate);
        return guestMapper.toDto(updatedGuest);
    }

    @Override
    public void delete(Long id) {
        guestRepository.delete(id);

    }

}
