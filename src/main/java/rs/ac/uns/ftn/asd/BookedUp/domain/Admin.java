package rs.ac.uns.ftn.asd.BookedUp.domain;

import rs.ac.uns.ftn.asd.BookedUp.dto.UserDetailedInDTO;

import java.util.List;

public class Admin extends User{
    private List<UserReport> userReports;
    private List<ReviewReport> reviewReports;
    private List<Accommodation> requests;
    public Admin(){ super(); }
    public Admin(
            Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, Role role,
            List<UserReport> userReports, List<ReviewReport> reviewReports, List<Accommodation> requests) {
        super(id, firstName, lastName, address, phone, email, password, role);
        this.userReports = userReports;
        this.reviewReports = reviewReports;
        this.requests = requests;
    }

    public List<UserReport> getUserReports() {return userReports;}

    public void setUserReports(List<UserReport> userReports) {this.userReports = userReports;}

    public List<ReviewReport> getReviewReports() {
        return reviewReports;
    }

    public void setReviewReports(List<ReviewReport> reviewReports) {
        this.reviewReports = reviewReports;
    }

    public List<Accommodation> getRequests() {return requests;}

    public void setRequests(List<Accommodation> requests) {this.requests = requests;}
    public void copyValues(Admin admin) {
        super.copyValues(admin);
        this.userReports = admin.getUserReports();
        this.reviewReports = admin.getReviewReports();
        this.requests = admin.getRequests();
    }

    public void copyValuesFromDTO(UserDetailedInDTO userDTO) {
        super.copyValuesFromDTO(userDTO);
        this.setRole(Role.ADMIN);
        this.setUserReports(null);
        this.setReviewReports(null);
        this.setRequests(null);
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
