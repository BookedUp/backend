package rs.ac.uns.ftn.asd.BookedUp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("host")
public class Host extends User {

    @Column(unique = false, nullable = true)
    private double averageRating;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private List<Accommodation> accommodations;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "host_id", nullable = true)
//    private List<Reservation> requests;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "host_id", nullable = true)
    private List<Statistics> statistics;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "host_id", nullable = true)
    private List<AccommodationStatistics> accommodationStatistics;

    @Column(nullable = true)
    private boolean reservationCreatedNotificationEnabled = true;

    @Column(nullable = true)
    private boolean cancellationNotificationEnabled = true;

    @Column(nullable = true)
    private boolean hostRatingNotificationEnabled = true;

    @Column(nullable = true)
    private boolean accommodationRatingNotificationEnabled = true;


    public Host(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, boolean isBlocked, boolean active, boolean verified, Photo profilePicture, Set<Authority> authority, Timestamp lastPasswordResetDate, List<Notification> notifications, double averageRating, List<Accommodation> accommodations, List<Statistics> statistics, List<AccommodationStatistics> accommodationStatistics, boolean reservationCreatedNotificationEnabled, boolean cancellationNotificationEnabled, boolean hostRatingNotificationEnabled, boolean accommodationRatingNotificationEnabled) {
        super(id, firstName, lastName, address, phone, email, password, isBlocked, active, verified, profilePicture, authority, lastPasswordResetDate, notifications);
        this.averageRating = averageRating;
        this.accommodations = accommodations;
        //this.requests = requests;
        this.statistics = statistics;
        this.accommodationStatistics = accommodationStatistics;
        this.reservationCreatedNotificationEnabled = reservationCreatedNotificationEnabled;
        this.cancellationNotificationEnabled = cancellationNotificationEnabled;
        this.hostRatingNotificationEnabled = hostRatingNotificationEnabled;
        this.accommodationRatingNotificationEnabled = accommodationRatingNotificationEnabled;
    }

    public void copyValues(Host host) {
        super.copyValues(host);
        this.averageRating = host.getAverageRating();
        this.accommodations = host.getAccommodations();
        //this.notifications = host.getNotifications();
        //this.requests = host.getRequests();
        this.reservationCreatedNotificationEnabled = host.isReservationCreatedNotificationEnabled();
        this.cancellationNotificationEnabled = host.isCancellationNotificationEnabled();
        this.hostRatingNotificationEnabled = host.isHostRatingNotificationEnabled();
        this.accommodationRatingNotificationEnabled = host.isAccommodationRatingNotificationEnabled();
        this.statistics = host.getStatistics();
        this.accommodationStatistics = host.getAccommodationStatistics();
    }


}
