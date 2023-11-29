package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDTO {
    private Long id;
    private Date fromDate;
    private Date toDate;
    private double profit;
    private int numberOfReservations;
    private List<StatisticsDetailDTO> details;


    public void copyValues(StatisticsDTO dto) {
        this.fromDate = dto.getFromDate();
        this.toDate = dto.getToDate();
        this.profit = dto.getProfit();
        this.numberOfReservations = dto.getNumberOfReservations();
        this.details = dto.getDetails();
    }
}
