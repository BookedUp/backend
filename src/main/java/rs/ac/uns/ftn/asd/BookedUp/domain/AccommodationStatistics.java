package rs.ac.uns.ftn.asd.BookedUp.domain;

import jakarta.persistence.*;
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
@Entity
public class AccommodationStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private double profit;

    @Column(nullable = false)
    private int numberOfReservations;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_id", nullable = true)
    private List<StatisticsDetail> details;

    public void copyValues(AccommodationStatistics accommodationReport) {
        this.accommodation = accommodationReport.getAccommodation();
        this.year = accommodationReport.getYear();
        this.profit = accommodationReport.getProfit();
        this.numberOfReservations = accommodationReport.getNumberOfReservations();

        this.details = new ArrayList<>(accommodationReport.getDetails());
    }
}
