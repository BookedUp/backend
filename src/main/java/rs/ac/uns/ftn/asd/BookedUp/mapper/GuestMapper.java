package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.*;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Component
public class GuestMapper implements MapperInterface<Guest, GuestDTO>{

    AccommodationMapper accommodationMapper = new AccommodationMapper();
    ReviewMapper reviewMapper = new ReviewMapper();
    ReservationMapper reservationMapper = new ReservationMapper();
    NotificationMapper notificationMapper = new NotificationMapper();
    @Override
    public Guest toEntity(GuestDTO dto) {
        if (dto == null) {
            return null;
        }
        List<Accommodation> favourites = new ArrayList<Accommodation>();
        if(dto.getFavourites() != null) {
            for(AccommodationDTO accommodationDTO : dto.getFavourites())
                favourites.add(accommodationMapper.toEntity(accommodationDTO));
        }

        List<Reservation> reservations = new ArrayList<Reservation>();
        if(dto.getReservations() != null) {
            for(ReservationDTO reservationDTO : dto.getReservations())
                reservations.add(reservationMapper.toEntity(reservationDTO));
        }

        List<Reservation> requests = new ArrayList<Reservation>();
        if(dto.getRequests() != null) {
            for(ReservationDTO reservationDTO : dto.getRequests())
                requests.add(reservationMapper.toEntity(reservationDTO));
        }

        List<Review> reviews = new ArrayList<Review>();
        if(dto.getReviews() != null) {
            for(ReviewDTO reviewDTO : dto.getReviews())
                reviews.add(reviewMapper.toEntity(reviewDTO));
        }

        List<Notification> notifications = new ArrayList<Notification>();
        if(dto.getNotifications() != null) {
            for(NotificationDTO notificationDTO : dto.getNotifications())
                notifications.add(notificationMapper.toEntity(notificationDTO));
        }

        return new Guest(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getPhone(), dto.getEmail(), dto.getPassword(), Role.GUEST, dto.isBlocked(), notifications, requests, reservations, favourites, reviews, dto.isNotificationEnable());
    }

    @Override
    public GuestDTO toDto(Guest entity) {
        if (entity == null) {
            return null;
        }
        List<AccommodationDTO> favouritesDTOS = new ArrayList<AccommodationDTO>();
        if(entity.getFavourites() != null) {
            for(Accommodation accommodation : entity.getFavourites())
                favouritesDTOS.add(accommodationMapper.toDto(accommodation));
        }

        List<ReservationDTO> reservationDTOS = new ArrayList<ReservationDTO>();
        if(entity.getReservations() != null) {
            for(Reservation reservation : entity.getReservations())
                reservationDTOS.add(reservationMapper.toDto(reservation));
        }

        List<ReservationDTO> requestsDTOS = new ArrayList<ReservationDTO>();
        if(entity.getRequests() != null) {
            for(Reservation reservation : entity.getRequests())
                requestsDTOS.add(reservationMapper.toDto(reservation));
        }

        List<ReviewDTO> reviewDTOS = new ArrayList<ReviewDTO>();
        if(entity.getReviews() != null) {
            for(Review review : entity.getReviews())
                reviewDTOS.add(reviewMapper.toDto(review));
        }

        List<NotificationDTO> notificationDTOS = new ArrayList<NotificationDTO>();
        if(entity.getNotifications() != null) {
            for(Notification notification : entity.getNotifications())
                notificationDTOS.add(notificationMapper.toDto(notification));
        }

        return new GuestDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getPhone(), entity.getEmail(), entity.getPassword(), entity.isBlocked(), notificationDTOS, requestsDTOS, reservationDTOS, favouritesDTOS, reviewDTOS, entity.isNotificatonEnable());

    }
}
