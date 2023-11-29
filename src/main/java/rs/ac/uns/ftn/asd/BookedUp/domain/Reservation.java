package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private Long id;
    private LocalDateTime createdTime;
    private Date startDate;
    private Date endDate;
    private double totalPrice;
    private int guestsNumber;
    private Accommodation accommodation;
    private Guest guest;
    private ReservationStatus status;

    public Reservation(LocalDateTime createdTime, Date startDate, Date endDate, double totalPrice, int guestsNumber, Accommodation accommodation, Guest guest, ReservationStatus status) {
        this.createdTime = createdTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.guestsNumber = guestsNumber;
        this.accommodation = accommodation;
        this.guest = guest;
        this.status = status;
    }

    public void copyValues(Reservation reservation) {
        this.id = reservation.getId();
        this.createdTime = reservation.getCreatedTime();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.totalPrice = reservation.getTotalPrice();
        this.guestsNumber = reservation.getGuestsNumber();
        this.accommodation = reservation.getAccommodation();
        this.guest = reservation.getGuest();
        this.status = reservation.getStatus();
    }

}
