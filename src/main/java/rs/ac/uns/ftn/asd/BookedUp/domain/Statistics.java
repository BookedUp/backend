package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    private Long id;
    private Date fromDate;
    private Date toDate;
    private double profit;
    private int numberOfReservations;
    private List<StatisticsDetail> details;

    public void copyValues(Statistics report) {
        this.fromDate = report.getFromDate();
        this.toDate = report.getToDate();
        this.profit = report.getProfit();
        this.numberOfReservations = report.getNumberOfReservations();

        this.details = new ArrayList<>(report.getDetails());
    }
}

