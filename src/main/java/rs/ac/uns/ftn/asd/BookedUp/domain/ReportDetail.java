package rs.ac.uns.ftn.asd.BookedUp.domain;

public class ReportDetail {
    private Long id;
    private String key;
    private double profit;
    private int numberOfReservations;

    public ReportDetail() {
    }

    public ReportDetail(Long id, String key, double profit, int numberOfReservations) {
        this.id = id;
        this.key = key;
        this.profit = profit;
        this.numberOfReservations = numberOfReservations;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.key = reportDetail.getKey();
        this.profit = reportDetail.getProfit();
        this.numberOfReservations = reportDetail.getNumberOfReservations();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

