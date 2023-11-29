package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReport {
    private Long id;
    private String reason;
    private User reportedUser;
    private boolean status;

    public void copyValues(UserReport userReport) {
        this.reason = userReport.getReason();
        this.reportedUser = userReport.getReportedUser();
        this.status = userReport.isStatus();
    }
}
