package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.List;

public class Admin extends User{
    private List<UserReport> reports;
    private List<Reservation> requests;
    public Admin(){ super(); }
    public Admin(
            Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, Role role,
            List<UserReport> reports, List<Reservation> requests) {
        super(id, firstName, lastName, address, phone, email, password, role);
        this.reports = reports;
        this.requests = requests;
    }

    public List<UserReport> getReports() {return reports;}

    public void setReports(List<UserReport> reports) {this.reports = reports;}

    public List<Reservation> getRequests() {return requests;}

    public void setRequests(List<Reservation> requests) {this.requests = requests;}
    public void copyValues(Admin admin) {
        super.copyValues(admin);
        this.reports = admin.getReports();
        this.requests = admin.getRequests();
    }

    // Method to block a user
    public void blockUser(Long userID) {
        // TODO: Implement method to block a user
    }

    // Method to unblock a user
    public void unblockUser(Long userID) {
        // TODO: Implement method to unblock a user
    }

    // Method to accept an accommodation
    public void acceptAccommodation(Long accommodationId) {
        // TODO: Implement method to accept an accommodation
    }

    // Method to reject an accommodation
    public void rejectAccommodation(Long accommodationId) {
        // TODO: Implement method to reject an accommodation
    }

    // Method to accept a review
    public void acceptReview(Long reviewId) {
        // TODO: Implement method to accept a review
    }

    // Method to reject a review
    public void rejectReview(Long reviewId) {
        // TODO: Implement method to reject a review
    }
}
