package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.GuestDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.HostDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.mapper.GuestMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService implements ServiceInterface<Guest>{
    @Autowired
    private IGuestRepository repository;

    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private IPhotoRepository photoRepository;

    @Autowired
    private IReviewRepository reviewRepository;
    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private GuestMapper guestMapper;
    @Override
    public Collection<Guest> getAll() {
        return repository.findAllGuest();
    }

    @Override
    public Guest getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Guest create(Guest guest) throws Exception {
        if (guest.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        return repository.save(guest);
    }

    @Override
    public Guest save(Guest guest) throws Exception {
        return repository.save(guest);
    }

//    @Override
//    public GuestDTO update(GuestDTO guestDto) throws Exception {
//        Guest guest = guestMapper.toEntity(guestDto);
//        Guest guestToUpdate = repository.findById(guestDto.getId()).orElse(null);;
//        if (guestToUpdate == null) {
//            throw new Exception("The requested entity was not found.");
//        }
//        guestToUpdate.setFirstName(guest.getFirstName());
//        guestToUpdate.setLastName(guest.getLastName());
//        guestToUpdate.setAddress(guest.getAddress());
//        guestToUpdate.setEmail(guest.getEmail());
//        guestToUpdate.setPassword(guest.getPassword());
//        guestToUpdate.setPhone(guest.getPhone());
//        //guestToUpdate.setRole(guest.getRole());
//        guestToUpdate.setVerified(guest.isVerified());
//        guestToUpdate.setProfilePicture(guest.getProfilePicture());
//        guestToUpdate.setLastPasswordResetDate(guest.getLastPasswordResetDate());
//        guestToUpdate.setBlocked(guest.isBlocked());
//        guestToUpdate.setRequests(guest.getRequests());
//        guestToUpdate.setReservations(guest.getReservations());
//        guestToUpdate.setFavourites(guest.getFavourites());
//        guestToUpdate.setReviews(guest.getReviews());
//        guestToUpdate.setNotifications(guest.getNotifications());
//        guestToUpdate.setNotificationEnable(guest.isNotificationEnable());
//
//        guestToUpdate.setAuthority(guest.getAuthority());
//        guestToUpdate.setProfilePicture(guest.getProfilePicture());
//        guestToUpdate.setVerified(guest.isVerified());
//        guestToUpdate.setLastPasswordResetDate(guest.getLastPasswordResetDate());
//
//        Guest updatedGuest = repository.save(guestToUpdate);
//        return guestMapper.toDto(updatedGuest);
//    }

    @Override
    public void delete(Long id) throws Exception {
        Guest guest = repository.findById(id).orElse(null);
        
        if (guest == null)
            throw new Exception("Guest doesn't exist");
        
        if (hasActiveReservations(guest)) {
            throw new Exception("Guest has active reservations and cannot be deleted");
        }

        List<Reservation> reservations = guest.getReservations();
        if(!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                reservation.setActive(false);
                reservationRepository.save(reservation);
            }
        }

        List<Accommodation> favorites = guest.getFavourites();
        if (!favorites.isEmpty()) {
            favorites.clear();
            guest.setFavourites(favorites);

        }

        List<Review> reviews = guest.getReviews();
        if(!reviews.isEmpty()) {
            for (Review review : reviews) {
                review.setIsReviewActive(false);
                reviewRepository.save(review);
            }
        }
        //Dalje

        Address address = guest.getAddress();
        if(address != null){
            address.setActive(false);
        }

        Photo profilePhoto = guest.getProfilePicture();
        if(profilePhoto != null){
            profilePhoto.setActive(false);
            photoRepository.save(profilePhoto);
        }

        List<Notification> notifications = guest.getNotifications();
        if(!notifications.isEmpty()) {
            for (Notification notification : notifications) {
                notification.setActive(false);
                notificationRepository.save(notification);
            }
        }



        guest.setActive(false);
        repository.save(guest);
    }

    private boolean hasActiveReservations(Guest guest) {
        if (guest.getReservations() != null) {
            return guest.getReservations().stream()
                    .anyMatch(reservation -> reservation.getStatus() != ReservationStatus.CANCELLED
                            && reservation.getStatus() != ReservationStatus.COMPLETED
                            && reservation.getStatus() != ReservationStatus.REJECTED);
        }
        return false;
    }



}
