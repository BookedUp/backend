package rs.ac.uns.ftn.asd.BookedUp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.DateRange;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceChangeDTO {
    private Long id;
    private Date changeDate;
    private double newPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceChangeDTO that = (PriceChangeDTO) o;
        return Double.compare(newPrice, that.newPrice) == 0 && Objects.equals(id, that.id) && Objects.equals(changeDate, that.changeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, changeDate, newPrice);
    }
}
