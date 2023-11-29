package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Admin extends User{
    private List<UserReport> userReports;
    private List<ReviewReport> reviewReports;
    private List<Accommodation> requests;

    public void copyValues(Admin admin) {
        super.copyValues(admin);
        this.userReports = admin.getUserReports();
        this.reviewReports = admin.getReviewReports();
        this.requests = admin.getRequests();
    }

    public void copyValuesFromDTO(UserDTO userDTO) {
        super.copyValuesFromDTO(userDTO);
        this.setRole(Role.ADMIN);
        this.setUserReports(null);
        this.setReviewReports(null);
        this.setRequests(null);
    }

}
