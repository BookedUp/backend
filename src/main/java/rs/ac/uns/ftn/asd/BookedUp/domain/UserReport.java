package rs.ac.uns.ftn.asd.BookedUp.domain;

public class UserReport {
    private Long id;
    private String reason;
    private User reportedUser;
    private boolean status;

    public UserReport() {
    }

    public UserReport(Long id, String reason, User reportedUser, boolean status) {
        this.id = id;
        this.reason = reason;
        this.reportedUser = reportedUser;
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

    public User getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(User reportedUser) {
        this.reportedUser = reportedUser;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public void copyValues(UserReport userReport) {
        this.reason = userReport.getReason();
        this.reportedUser = userReport.getReportedUser();
        this.status = userReport.isStatus();
    }
}
