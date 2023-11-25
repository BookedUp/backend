package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Guest extends User {
    private boolean isBlocked;
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
        super(id, firstName, lastName, address, phone, email, password, role);
        this.isBlocked = isBlocked;
        this.requests = requests;
        this.reservations = reservations;
        this.favourites = favourites;
        this.reviews = reviews;
        this.notifications = notifications;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
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
        this.isBlocked = guest.isBlocked();
        this.requests = guest.getRequests();
        this.reservations = guest.getReservations();
        this.favourites = guest.getFavourites();
        this.reviews = guest.getReviews();
        this.notifications = guest.getNotifications();
    }
}

