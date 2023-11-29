package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationStatistics {
    private Long id;
    private Accommodation accommodation;
    private int year;
    private double profit;
    private int numberOfReservations;
    private List<StatisticsDetail> details;


    public void copyValues(AccommodationStatistics accommodationReport) {
        this.accommodation = accommodationReport.getAccommodation();
        this.year = accommodationReport.getYear();
        this.profit = accommodationReport.getProfit();
        this.numberOfReservations = accommodationReport.getNumberOfReservations();

        this.details = new ArrayList<>(accommodationReport.getDetails());
    }
}
