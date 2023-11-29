package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Guest extends User {

    private List<Reservation> requests;
    private List<Reservation> reservations;
    private List<Accommodation> favourites;
    private List<Review> reviews;
    private List<Notification> notifications;
    private boolean notificatonEnable;

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

}

