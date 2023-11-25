package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.List;

public class Host extends User {
    private boolean isBlocked;
    private double averageRating;
    private List<Accommodation> properties;
    private List<Notification> notifications;
    private List<Reservation> requests;

    public Host() {
        super();
    }

    public Host(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, Role role,
                boolean isBlocked, double averageRating, List<Accommodation> properties, List<Notification> notifications, List<Reservation> requests) {
        super(id, firstName, lastName, address, phone, email, password, role);
        this.isBlocked = isBlocked;
        this.averageRating = averageRating;
        this.properties = properties;
        this.notifications = notifications;
        this.requests = requests;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Accommodation> getProperties() {return properties;}

    public void setProperties(List<Accommodation> properties) {this.properties = properties;}

    public List<Notification> getNotifications() {return notifications;}

    public void setNotifications(List<Notification> notifications) {this.notifications = notifications;}

    public List<Reservation> getRequests() {return requests;}

    public void setRequests(List<Reservation> requests) {
        this.requests = requests;
    }

    public void copyValues(Host host) {
        super.copyValues(host);
        this.isBlocked = host.isBlocked();
        this.averageRating = host.getAverageRating();
        this.properties = host.getProperties();
        this.notifications = host.getNotifications();
        this.requests = host.getRequests();
    }

}
