package rs.ac.uns.ftn.asd.BookedUp.dto;

import rs.ac.uns.ftn.asd.BookedUp.domain.*;

import java.util.List;

public class GuestDTO extends UserDTO {

    private List<ReservationDTO> requests;
    private List<ReservationDTO> reservations;
    private List<AccommodationDTO> favourites;
    private List<Review> reviews;
    private List<Notification> notifications;

    public GuestDTO() {
    }

    public GuestDTO(List<ReservationDTO> requests, List<ReservationDTO> reservations, List<AccommodationDTO> favourites, List<Review> reviews, List<Notification> notifications) {
        this.requests = requests;
        this.reservations = reservations;
        this.favourites = favourites;
        this.reviews = reviews;
        this.notifications = notifications;
    }

    public GuestDTO(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, List<ReservationDTO> requests, List<ReservationDTO> reservations, List<AccommodationDTO> favourites, List<Review> reviews, List<Notification> notifications) {
        super(id, firstName, lastName, address, phone, email, password);
        this.requests = requests;
        this.reservations = reservations;
        this.favourites = favourites;
        this.reviews = reviews;
        this.notifications = notifications;
    }

    public List<ReservationDTO> getRequests() {
        return requests;
    }

    public void setRequests(List<ReservationDTO> requests) {
        this.requests = requests;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public List<AccommodationDTO> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<AccommodationDTO> favourites) {
        this.favourites = favourites;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public void copyValues(GuestDTO dto) {
        super.copyValues(dto);
        this.favourites = dto.getFavourites();
        this.requests = dto.getRequests();
        this.reservations = dto.getReservations();
        this.reviews = dto.getReviews();
    }
}
