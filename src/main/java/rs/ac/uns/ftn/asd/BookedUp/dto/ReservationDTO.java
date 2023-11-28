package rs.ac.uns.ftn.asd.BookedUp.dto;

import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;

import java.util.Date;

public class ReservationDTO {

    private AccommodationDTO accommodationDTO;

    private Date startDate;

    private Date endDate;

    private Integer guestsNumber;

    public ReservationDTO() {
    }

    public ReservationDTO(AccommodationDTO accommodationDTO, Date startDate, Date endDate, Integer guestsNumber) {
        this.accommodationDTO = accommodationDTO;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestsNumber = guestsNumber;
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
}
