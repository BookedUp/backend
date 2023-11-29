package rs.ac.uns.ftn.asd.BookedUp.domain;

import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;

import java.util.Date;
import java.util.List;


public class Guest extends User {

    private List<Reservation> requests;
    private List<Reservation> reservations;
    private List<Accommodation> favourites;
    private List<Review> reviews;
    private List<Notification> notifications;

    public Guest() {
        super();
    }

    public Guest(
            Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, Role role,
            boolean isBlocked, List<Reservation> requests, List<Reservation> reservations, List<Accommodation> favourites, List<Review> reviews, List<Notification> notifications) {
        super(id, firstName, lastName, address, phone, email, password, role,isBlocked);

        this.requests = requests;
        this.reservations = reservations;
        this.favourites = favourites;
        this.reviews = reviews;
        this.notifications = notifications;
    }



    public List<Reservation> getRequests() {return requests;}

    public void setRequests(List<Reservation> requests) {this.requests = requests;}

    public List<Reservation> getReservations() {return reservations;}

    public void setReservations(List<Reservation> reservations) {this.reservations = reservations;}

    public List<Accommodation> getFavourites() {return favourites;}

    public void setFavourites(List<Accommodation> favourites) {this.favourites = favourites;}

    public List<Review> getReviews() {return reviews;}

    public void setReviews(List<Review> reviews) {this.reviews = reviews;}

    public List<Notification> getNotifications() {return notifications;}

    public void setNotifications(List<Notification> notifications) {this.notifications = notifications;}
    public void copyValues(Guest guest) {
        super.copyValues(guest);
        this.requests = guest.getRequests();
        this.reservations = guest.getReservations();
        this.favourites = guest.getFavourites();
        this.reviews = guest.getReviews();
        this.notifications = guest.getNotifications();
    }

    public void copyValuesFromDTO(UserDTO userDTO) {
        super.copyValuesFromDTO(userDTO);
        this.setRole(Role.GUEST);
        this.setRequests(null);
        this.setNotifications(null);
        this.setReservations(null);
        this.setReviews(null);
        this.setFavourites(null);
    }

    // Method to delete the guest account
    public void deleteAccount() {
        // TODO: Implement method to delete the guest account
    }

    // Method to create a reservation
    public void createReservation(Long accommodationId, Date checkInDate, Date checkOutDate) {
        // TODO: Implement method to create a reservation
    }

    // Method to cancel a reservation
    public void cancelReservation(Long reservationId) {
        // TODO: Implement method to cancel a reservation
    }

    // Method to review an accommodation
    public void reviewAccommodation(Long accId, int rating, String comment) {
        // TODO: Implement method to review an accommodation
    }

    // Method to review an owner
    public void reviewOwner(Long ownerId, String reviewText, int rating) {
        // TODO: Implement method to review an owner
    }

    // Method to delete a review
    public void deleteReview(Long reviewId) {
        // TODO: Implement method to delete a review
    }

    // Method to report an owner
    public void reportOwner(Long ownerId, String reportReason) {
        // TODO: Implement method to report an owner
    }

    // Method to save an accommodation to favorites
    public void saveToFavorites(Long accommodationId) {
        // TODO: Implement method to save an accommodation to favorites
    }
}

