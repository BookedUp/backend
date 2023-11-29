package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.Review;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewReportDTO {
    private Long id;
    private String reason;
    private ReviewDTO reportedReviewDTO;
    private boolean status;

    public void copyValues(ReviewReportDTO dto) {
        this.reason = dto.getReason();
        this.reportedReviewDTO = dto.getReportedReviewDTO();
        this.status = dto.isStatus();

    }
}
