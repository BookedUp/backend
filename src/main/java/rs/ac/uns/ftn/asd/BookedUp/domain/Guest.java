package rs.ac.uns.ftn.asd.BookedUp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("guest")
public class Guest extends User {

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Reservation> requests;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @ManyToMany
    @JoinTable(
            name = "guest_favourite",
            joinColumns = @JoinColumn(name = "guest_id"),
            inverseJoinColumns = @JoinColumn(name = "accommodation_id"))
    private List<Accommodation> favourites;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Review> reviews;

//    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
//    private List<Notification> notifications;

    @Column(nullable = false)
    private boolean notificatonEnable;

    public Guest(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, Role role, boolean isBlocked, List<Notification> notifications, List<Reservation> requests, List<Reservation> reservations, List<Accommodation> favourites, List<Review> reviews, boolean notificatonEnable) {
        super(id, firstName, lastName, address, phone, email, password, role, isBlocked, notifications);
        this.requests = requests;
        this.reservations = reservations;
        this.favourites = favourites;
        this.reviews = reviews;
        this.notificatonEnable = notificatonEnable;
    }

    public void copyValues(Guest guest) {
        super.copyValues(guest);
        this.requests = guest.getRequests();
        this.reservations = guest.getReservations();
        this.favourites = guest.getFavourites();
        this.reviews = guest.getReviews();
        //this.notifications = guest.getNotifications();
    }


}

