package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.enums.ReservationStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long id;

    private AccommodationDTO accommodationDTO;

    private Date startDate;

    private Date endDate;

    private Integer guestsNumber;

    private ReservationStatus status;

    public ReservationDTO(AccommodationDTO accommodationDTO, Date startDate, Date endDate, Integer guestsNumber, ReservationStatus status) {
        this.accommodationDTO = accommodationDTO;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestsNumber = guestsNumber;
        this.status = status;
    }
    public void copyValues(ReservationDTO dto) {
        this.accommodationDTO = dto.getAccommodationDTO();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.guestsNumber = dto.getGuestsNumber();
        this.status = dto.getStatus();
    }
}
