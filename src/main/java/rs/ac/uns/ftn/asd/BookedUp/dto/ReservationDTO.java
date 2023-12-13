package rs.ac.uns.ftn.asd.BookedUp.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private double totalPrice;
    private int guestsNumber;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private AccommodationDTO accommodation;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private GuestDTO guest;
    private ReservationStatus status;

    public void copyValues(ReservationDTO dto) {
        this.accommodation = dto.getAccommodation();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.guestsNumber = dto.getGuestsNumber();
        this.status = dto.getStatus();
    }
}
