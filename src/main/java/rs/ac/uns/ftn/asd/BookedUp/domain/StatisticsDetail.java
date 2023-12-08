package rs.ac.uns.ftn.asd.BookedUp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StatisticsDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String key;

    @Column(nullable = false)
    private double profit;

    @Column(nullable = false)
    private int numberOfReservations;


    public void copyValues(StatisticsDetail reportDetail) {
        this.key = reportDetail.getKey();
        this.profit = reportDetail.getProfit();
        this.numberOfReservations = reportDetail.getNumberOfReservations();
    }
}

