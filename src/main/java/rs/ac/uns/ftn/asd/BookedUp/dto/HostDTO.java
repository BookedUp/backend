package rs.ac.uns.ftn.asd.BookedUp.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class HostDTO extends UserDTO {

    private double averageRating;
//    private List<NotificationDTO> notifications;
    //private List<ReservationDTO> requests;

    private boolean reservationCreatedNotificationEnabled = true;
    private boolean cancellationNotificationEnabled = true;
    private boolean hostRatingNotificationEnabled = true;
    private boolean accommodationRatingNotificationEnabled = true;


    public HostDTO(Long id, String firstName, String lastName, AddressDTO address, Integer phone, String email, String password, boolean isBlocked, boolean verified, boolean active, PhotoDTO profilePicture, List<NotificationDTO> notifications, double averageRating, boolean reservationCreatedNotificationEnabled, boolean cancellationNotificationEnabled, boolean hostRatingNotificationEnabled, boolean accommodationRatingNotificationEnabled) {
        super(id, firstName, lastName, address, phone, email, password, isBlocked, verified, active, profilePicture, notifications);
        this.averageRating = averageRating;
        this.reservationCreatedNotificationEnabled = reservationCreatedNotificationEnabled;
        this.cancellationNotificationEnabled = cancellationNotificationEnabled;
        this.hostRatingNotificationEnabled = hostRatingNotificationEnabled;
        this.accommodationRatingNotificationEnabled = accommodationRatingNotificationEnabled;
    }


}
