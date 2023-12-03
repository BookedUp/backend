package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class HostDTO extends UserDTO {

    private double averageRating;
    private List<AccommodationDTO> accommodations;
//    private List<NotificationDTO> notifications;
    private List<ReservationDTO> requests;
    private List<StatisticsDTO> statistics;
    private List<AccommodationStatisticsDTO> accommodationStatistics;

    private boolean reservationCreatedNotificationEnabled = true;
    private boolean cancellationNotificationEnabled = true;
    private boolean hostRatingNotificationEnabled = true;
    private boolean accommodationRatingNotificationEnabled = true;

    public HostDTO(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, boolean isBlocked, List<NotificationDTO> notifications, double averageRating, List<AccommodationDTO> accommodations, List<ReservationDTO> requests, List<StatisticsDTO> statistics, List<AccommodationStatisticsDTO> accommodationStatistics, boolean reservationCreatedNotificationEnabled, boolean cancellationNotificationEnabled, boolean hostRatingNotificationEnabled, boolean accommodationRatingNotificationEnabled) {
        super(id, firstName, lastName, address, phone, email, password, isBlocked, notifications);
        this.averageRating = averageRating;
        this.accommodations = accommodations;
        this.requests = requests;
        this.statistics = statistics;
        this.accommodationStatistics = accommodationStatistics;
        this.reservationCreatedNotificationEnabled = reservationCreatedNotificationEnabled;
        this.cancellationNotificationEnabled = cancellationNotificationEnabled;
        this.hostRatingNotificationEnabled = hostRatingNotificationEnabled;
        this.accommodationRatingNotificationEnabled = accommodationRatingNotificationEnabled;
    }

    public void copyValues(HostDTO dto) {
        super.copyValues(dto);
        this.accommodations = dto.getAccommodations();
        this.requests = dto.getRequests();
        //this.notifications = dto.getNotifications();
        this.averageRating = dto.getAverageRating();
        this.reservationCreatedNotificationEnabled = dto.isReservationCreatedNotificationEnabled();
        this.cancellationNotificationEnabled = dto.isCancellationNotificationEnabled();
        this.hostRatingNotificationEnabled = dto.isHostRatingNotificationEnabled();
        this.accommodationRatingNotificationEnabled = dto.isAccommodationRatingNotificationEnabled();
        this.statistics = dto.getStatistics();
        this.accommodationStatistics = dto.getAccommodationStatistics();
    }
}
