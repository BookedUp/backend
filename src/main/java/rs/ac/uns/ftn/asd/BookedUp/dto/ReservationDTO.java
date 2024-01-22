package rs.ac.uns.ftn.asd.BookedUp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private AccommodationDTO accommodation;
    private GuestDTO guest;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ReservationStatus status;

    public ReservationDTO(Date startDate, Date endDate, double totalPrice, int guestsNumber, AccommodationDTO accommodation, GuestDTO guest, ReservationStatus status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.guestsNumber = guestsNumber;
        this.accommodation = accommodation;
        this.guest = guest;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalPrice=" + totalPrice +
                ", guestsNumber=" + guestsNumber +
                ", accommodation=" + accommodation +
                ", guest=" + guest +
                ", status=" + status +
                '}';
    }
}
