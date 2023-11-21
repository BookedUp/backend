package rs.ac.uns.ftn.asd.BookedUp.domain;

public class ReportDetail {
    private Long accommodationId;
    private double profit;
    private int numberOfReservations;

    public ReportDetail() {
    }

    public ReportDetail(Long accommodationId, double profit, int numberOfReservations) {
        this.accommodationId = accommodationId;
        this.profit = profit;
        this.numberOfReservations = numberOfReservations;
    }

    // Getters and setters

    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(int numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }
    public void copyValues(ReportDetail reportDetail) {
        this.accommodationId = reportDetail.getAccommodationId();
        this.profit = reportDetail.getProfit();
        this.numberOfReservations = reportDetail.getNumberOfReservations();
    }
}

