package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GuestDTO extends UserDTO {

    //private List<ReservationDTO> requests;
//    private List<ReservationDTO> reservations;
    private List<AccommodationDTO> favourites;
//    private List<ReviewDTO> reviews;
    private boolean notificationEnable = true;


    public GuestDTO(Long id, String firstName, String lastName, AddressDTO address, Integer phone, String email, String password, boolean isBlocked, boolean verified, PhotoDTO profilePicture, List<AccommodationDTO> favourites, boolean notificationEnable) {
        super(id, firstName, lastName, address, phone, email, password, isBlocked, verified, profilePicture);
        //this.requests = requests;
//        this.reservations = reservations;
        this.favourites = favourites;
//        this.reviews = reviews;
        this.notificationEnable = notificationEnable;
    }

    public void copyValues(GuestDTO dto) {
        super.copyValues(dto);
        this.favourites = dto.getFavourites();
        //this.requests = dto.getRequests();
//        this.reservations = dto.getReservations();
//        this.reviews = dto.getReviews();
        //this.notifications = dto.getNotifications();
        this.notificationEnable = dto.isNotificationEnable();
    }
}
