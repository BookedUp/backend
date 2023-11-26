package rs.ac.uns.ftn.asd.BookedUp.domain;

public class ReviewReport {
    private Long id;
    private String reason;
    private Review reportedReview;
    private boolean status;

    public ReviewReport() {
    }

    public ReviewReport(Long id, String reason, Review reportedReview, boolean status) {
        this.id = id;
        this.reason = reason;
        this.reportedReview = reportedReview;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public Review getReportedReview() {
        return reportedReview;
    }

    public void setReportedReview(Review reportedReview) {
        this.reportedReview = reportedReview;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void copyValues(ReviewReport reviewReport) {
        this.reason = reviewReport.getReason();
        this.reportedReview = reviewReport.getReportedReview();
        this.status = reviewReport.isStatus();
    }

}
