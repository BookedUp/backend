package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccommodationReport {
    private Long id;
    private Accommodation accommodation;
    private int year;
    private double profit;
    private int numberOfReservations;
    private List<ReportDetail> details;

    public AccommodationReport() {
    }
    public AccommodationReport(Long id, Accommodation accommodation, int year, double profit, int numberOfReservations, List<ReportDetail> details){
        this.id = id;
        this.accommodation = accommodation;
        this.year = year;
        this.profit = profit;
        this.numberOfReservations = numberOfReservations;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public List<ReportDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ReportDetail> details) {
        this.details = details;
    }

    public void copyValues(AccommodationReport accommodationReport) {
        this.accommodation = accommodationReport.getAccommodation();
        this.year = accommodationReport.getYear();
        this.profit = accommodationReport.getProfit();
        this.numberOfReservations = accommodationReport.getNumberOfReservations();

        this.details = new ArrayList<>(accommodationReport.getDetails());
    }
}
