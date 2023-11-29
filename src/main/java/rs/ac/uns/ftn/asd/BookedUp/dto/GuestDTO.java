package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class GuestDTO extends UserDTO {

    private List<ReservationDTO> requests;
    private List<ReservationDTO> reservations;
    private List<AccommodationDTO> favourites;
    private List<ReviewDTO> reviews;
    private List<NotificationDTO> notifications;
    private boolean notificationEnable;


    public void copyValues(GuestDTO dto) {
        super.copyValues(dto);
        this.favourites = dto.getFavourites();
        this.requests = dto.getRequests();
        this.reservations = dto.getReservations();
        this.reviews = dto.getReviews();
        this.notifications = dto.getNotifications();
        this.notificationEnable = dto.isNotificationEnable();
    }
}
