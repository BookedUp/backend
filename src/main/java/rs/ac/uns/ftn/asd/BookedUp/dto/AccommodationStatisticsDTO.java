package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationStatisticsDTO {
    private Long id;
    private AccommodationDTO accommodationDto;
    private int year;
    private double profit;
    private int numberOfReservations;
    private List<StatisticsDetailDTO> detailsDto;

    public void copyValues(AccommodationStatisticsDTO dto) {
        this.accommodationDto = dto.getAccommodationDto();
        this.year = dto.getYear();
        this.profit = dto.getProfit();
        this.numberOfReservations = dto.getNumberOfReservations();
        this.detailsDto = dto.getDetailsDto();
    }
}
