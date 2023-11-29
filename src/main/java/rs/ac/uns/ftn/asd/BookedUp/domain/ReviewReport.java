package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewReport {
    private Long id;
    private String reason;
    private Review reportedReview;
    private boolean status;

    public void copyValues(ReviewReport reviewReport) {
        this.reason = reviewReport.getReason();
        this.reportedReview = reviewReport.getReportedReview();
        this.status = reviewReport.isStatus();
    }

}
