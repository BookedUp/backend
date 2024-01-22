package rs.ac.uns.ftn.asd.BookedUp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PriceChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date changeDate;

    @Column(nullable = false)
    private double newPrice;

    public PriceChange(Date changeDate, double newPrice) {
        this.changeDate = changeDate;
        this.newPrice = newPrice;
    }

    //    @Column(nullable = false)
//    private boolean active = true;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceChange that = (PriceChange) o;
        return Double.compare(newPrice, that.newPrice) == 0 && Objects.equals(id, that.id) && Objects.equals(changeDate, that.changeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, changeDate, newPrice);
    }
}
