package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.List;

public class Host extends User {
    private List<Long> accommodationIds;
    private boolean isBlocked;
    private double averageRating;

    public Host() {
        super();
    }

    public Host(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, Role role,
                List<Long> accommodationIds, boolean isBlocked, double averageRating) {
        super(id, firstName, lastName, address, phone, email, password, role);
        this.accommodationIds = accommodationIds;
        this.isBlocked = isBlocked;
        this.averageRating = averageRating;
    }

    public List<Long> getAccommodationIds() {
        return accommodationIds;
    }

    public void setAccommodationIds(List<Long> accommodationIds) {
        this.accommodationIds = accommodationIds;
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

    public void copyValues(Host host) {
        super.copyValues(host);
        this.accommodationIds = host.accommodationIds;
        this.isBlocked = host.isBlocked;
        this.averageRating = host.averageRating;
    }
}
