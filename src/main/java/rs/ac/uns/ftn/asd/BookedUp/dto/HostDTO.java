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
public class HostDTO extends UserDTO {

    private double averageRating;
    private List<AccommodationDTO> properties;
    private List<NotificationDTO> notifications;
    private List<ReservationDTO> requests;
    private List<StatisticsDTO> statistics;
    private List<AccommodationStatisticsDTO> accommodationStatistics;

    private boolean reservationCreatedNotificationEnabled = true;
    private boolean cancellationNotificationEnabled = true;
    private boolean hostRatingNotificationEnabled = true;
    private boolean accommodationRatingNotificationEnabled = true;



    public void copyValues(HostDTO dto) {
        super.copyValues(dto);
        this.properties = dto.getProperties();
        this.requests = dto.getRequests();
        this.notifications = dto.getNotifications();
        this.averageRating = dto.getAverageRating();
        this.reservationCreatedNotificationEnabled = dto.isReservationCreatedNotificationEnabled();
        this.cancellationNotificationEnabled = dto.isCancellationNotificationEnabled();
        this.hostRatingNotificationEnabled = dto.isHostRatingNotificationEnabled();
        this.accommodationRatingNotificationEnabled = dto.isAccommodationRatingNotificationEnabled();
        this.statistics = dto.getStatistics();
        this.accommodationStatistics = dto.getAccommodationStatistics();
    }
}
