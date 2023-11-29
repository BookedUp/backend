package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Host extends User {

    private double averageRating;
    private List<Accommodation> properties;
    private List<Notification> notifications;
    private List<Reservation> requests;
    private List<Statistics> statistics;
    private List<AccommodationStatistics> accommodationStatistics;

    private boolean reservationCreatedNotificationEnabled = true;
    private boolean cancellationNotificationEnabled = true;
    private boolean hostRatingNotificationEnabled = true;
    private boolean accommodationRatingNotificationEnabled = true;


    public void copyValues(Host host) {
        super.copyValues(host);
        this.averageRating = host.getAverageRating();
        this.properties = host.getProperties();
        this.notifications = host.getNotifications();
        this.requests = host.getRequests();
        this.reservationCreatedNotificationEnabled = host.isReservationCreatedNotificationEnabled();
        this.cancellationNotificationEnabled = host.isCancellationNotificationEnabled();
        this.hostRatingNotificationEnabled = host.isHostRatingNotificationEnabled();
        this.accommodationRatingNotificationEnabled = host.isAccommodationRatingNotificationEnabled();
        this.statistics = host.getStatistics();
        this.accommodationStatistics = host.getAccommodationStatistics();
    }

    public void copyValuesFromDTO(UserDTO userDTO) {
        super.copyValuesFromDTO(userDTO);
        this.setRole(Role.HOST);
        this.setAverageRating(0);
        this.setNotifications(null);
        this.setProperties(null);
        this.setRequests(null);
        this.statistics = new ArrayList<Statistics>();
        this.accommodationStatistics = new ArrayList<AccommodationStatistics>();
    }

}
