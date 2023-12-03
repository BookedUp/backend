package rs.ac.uns.ftn.asd.BookedUp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("admin")
public class Admin extends User{
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id", nullable = true)
    private List<UserReport> userReports;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id", nullable = true)
    private List<ReviewReport> reviewReports;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id", nullable = true)
    private List<Accommodation> requests;

    public Admin(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, Role role, boolean isBlocked, List<Notification> notifications, List<UserReport> userReports, List<ReviewReport> reviewReports, List<Accommodation> requests) {
        super(id, firstName, lastName, address, phone, email, password, role, isBlocked, notifications);
        this.userReports = userReports;
        this.reviewReports = reviewReports;
        this.requests = requests;
    }

//    public void copyValues(Admin admin) {
//        super.copyValues(admin);
//        this.userReports = admin.getUserReports();
//        this.reviewReports = admin.getReviewReports();
//        this.requests = admin.getRequests();
//    }


}
