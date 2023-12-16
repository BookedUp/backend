package rs.ac.uns.ftn.asd.BookedUp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("guest")
public class Guest extends User {

//    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
//    private List<Reservation> requests;

//    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
//    private List<Reservation> reservations;

    @ManyToMany
    @JoinTable(
            name = "guest_favourite",
            joinColumns = @JoinColumn(name = "guest_id"),
            inverseJoinColumns = @JoinColumn(name = "accommodation_id"))
    private List<Accommodation> favourites;

//    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
//    private List<Review> reviews;

    @Column(nullable = true)
    private boolean notificationEnable = true;

    public Guest(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, boolean isBlocked, boolean active, boolean verified, Photo profilePicture, Set<Authority> authority, Timestamp lastPasswordResetDate, List<Accommodation> favourites, boolean notificationEnable) {
        super(id, firstName, lastName, address, phone, email, password, isBlocked, active, verified, profilePicture, authority, lastPasswordResetDate);
        this.favourites = favourites;
        this.notificationEnable = notificationEnable;
    }
}

