package rs.ac.uns.ftn.asd.BookedUp.dto;

import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Address;
import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;

import java.util.List;

public class HostDTO extends UserDTO {

    private double averageRating;
    private List<AccommodationDTO> properties;
    private List<Notification> notifications;
    private List<ReservationDTO> requests;

    public HostDTO() {
    }

    public HostDTO(double averageRating, List<AccommodationDTO> properties, List<Notification> notifications, List<ReservationDTO> requests) {
        this.averageRating = averageRating;
        this.properties = properties;
        this.notifications = notifications;
        this.requests = requests;
    }

    public HostDTO(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, double averageRating, List<AccommodationDTO> properties, List<Notification> notifications, List<ReservationDTO> requests) {
        super(id, firstName, lastName, address, phone, email, password);
        this.averageRating = averageRating;
        this.properties = properties;
        this.notifications = notifications;
        this.requests = requests;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<AccommodationDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<AccommodationDTO> properties) {
        this.properties = properties;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<ReservationDTO> getRequests() {
        return requests;
    }

    public void setRequests(List<ReservationDTO> requests) {
        this.requests = requests;
    }

    public void copyValues(HostDTO dto) {
        super.copyValues(dto);
        this.properties = dto.getProperties();
        this.requests = dto.getRequests();
        this.notifications = dto.getNotifications();
        this.averageRating = dto.getAverageRating();
    }
}
