package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.time.LocalDateTime;
import java.util.Date;

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


    public Reservation() {
    }

    public Reservation(Long id, LocalDateTime createdTime, Date startDate, Date endDate, double totalPrice, int guestsNumber, Accommodation accommodation, Guest guest, ReservationStatus status) {
        this.id = id;
        this.createdTime = createdTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.guestsNumber = guestsNumber;
        this.accommodation = accommodation;
        this.guest = guest;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
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

    // Method to check cancellation possibility
    public void isCancellationPossible() {
        // TODO: Implement method to check if cancellation is possible
    }
    // Method to check reviewing possibility
    public void isReviewingPossible() {
        // TODO: Implement method to check if reviewing is possible
    }
}
