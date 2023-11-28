package rs.ac.uns.ftn.asd.BookedUp.dto;

import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReservationStatus;

import java.util.Date;

public class ReservationDTO {

    private Long id;

    private AccommodationDTO accommodationDTO;

    private Date startDate;

    private Date endDate;

    private Integer guestsNumber;

    private ReservationStatus status;

    public ReservationDTO() {
    }

    public ReservationDTO(AccommodationDTO accommodationDTO, Date startDate, Date endDate, Integer guestsNumber, ReservationStatus status) {
        this.accommodationDTO = accommodationDTO;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestsNumber = guestsNumber;
        this.status = status;
    }

    public ReservationDTO(Long id, AccommodationDTO accommodationDTO, Date startDate, Date endDate, Integer guestsNumber, ReservationStatus status) {
        this.id = id;
        this.accommodationDTO = accommodationDTO;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestsNumber = guestsNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccommodationDTO getAccommodationDTO() {
        return accommodationDTO;
    }

    public void setAccommodationDTO(AccommodationDTO accommodationDTO) {
        this.accommodationDTO = accommodationDTO;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(Integer guestsNumber) {
        this.guestsNumber = guestsNumber;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
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
