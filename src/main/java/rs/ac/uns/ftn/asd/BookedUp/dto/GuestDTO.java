package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;

import java.util.List;

@Getter
@Setter
public class GuestDTO extends UserDTO {

    private List<ReservationDTO> requests;
    private List<ReservationDTO> reservations;
    private List<AccommodationDTO> favourites;
    private List<ReviewDTO> reviews;
    private List<NotificationDTO> notifications;

    public GuestDTO() {
    }

    public GuestDTO(List<ReservationDTO> requests, List<ReservationDTO> reservations, List<AccommodationDTO> favourites, List<ReviewDTO> reviews, List<NotificationDTO> notifications) {
        this.requests = requests;
        this.reservations = reservations;
        this.favourites = favourites;
        this.reviews = reviews;
        this.notifications = notifications;
    }

    public GuestDTO(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password,Boolean isBlocked, List<ReservationDTO> requests, List<ReservationDTO> reservations, List<AccommodationDTO> favourites, List<ReviewDTO> reviews, List<NotificationDTO> notifications) {
        super(id, firstName, lastName, address, phone, email, password, isBlocked);
        this.requests = requests;
        this.reservations = reservations;
        this.favourites = favourites;
        this.reviews = reviews;
        this.notifications = notifications;
    }



    public void copyValues(GuestDTO dto) {
        super.copyValues(dto);
        this.favourites = dto.getFavourites();
        this.requests = dto.getRequests();
        this.reservations = dto.getReservations();
        this.reviews = dto.getReviews();
        this.notifications = dto.getNotifications();
    }
}
