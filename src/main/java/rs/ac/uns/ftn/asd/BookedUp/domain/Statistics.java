package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Statistics {
    private Long id;
    private Date fromDate;
    private Date toDate;
    private double profit;
    private int numberOfReservations;
    private List<StatisticsDetail> details;

    public Statistics() {
    }

    public Statistics(Long id, Date fromDate, Date toDate, double profit, int numberOfReservations, List<StatisticsDetail> details) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.profit = profit;
        this.numberOfReservations = numberOfReservations;
        this.details = details;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    public List<StatisticsDetail> getDetails() {
        return details;
    }

    public void setDetails(List<StatisticsDetail> details) {
        this.details = details;
    }
    public void copyValues(Statistics report) {
        this.fromDate = report.getFromDate();
        this.toDate = report.getToDate();
        this.profit = report.getProfit();
        this.numberOfReservations = report.getNumberOfReservations();

        this.details = new ArrayList<>(report.getDetails());
    }
}

