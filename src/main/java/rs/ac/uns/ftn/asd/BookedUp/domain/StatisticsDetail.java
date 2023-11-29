package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDetail {
    private Long id;
    private String key;
    private double profit;
    private int numberOfReservations;
    public void copyValues(StatisticsDetail reportDetail) {
        this.key = reportDetail.getKey();
        this.profit = reportDetail.getProfit();
        this.numberOfReservations = reportDetail.getNumberOfReservations();
    }
}

