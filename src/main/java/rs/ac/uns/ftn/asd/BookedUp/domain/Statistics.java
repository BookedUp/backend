package rs.ac.uns.ftn.asd.BookedUp.domain;

import jakarta.persistence.*;
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
@Entity
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fromDate;

    @Column(nullable = false)
    private Date toDate;

    @Column(nullable = false)
    private double profit;

    @Column(nullable = false)
    private int numberOfReservations;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "statistics_id", nullable = true)
    private List<StatisticsDetail> details;


    public void copyValues(Statistics report) {
        this.fromDate = report.getFromDate();
        this.toDate = report.getToDate();
        this.profit = report.getProfit();
        this.numberOfReservations = report.getNumberOfReservations();

        this.details = new ArrayList<>(report.getDetails());
    }
}

