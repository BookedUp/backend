package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDetailDTO {

    private Long id;
    private String key;
    private double profit;
    private int numberOfReservations;

    public void copyValues(StatisticsDetailDTO dto) {
        this.key = dto.getKey();
        this.profit = dto.getProfit();
        this.numberOfReservations = dto.getNumberOfReservations();
    }
}
