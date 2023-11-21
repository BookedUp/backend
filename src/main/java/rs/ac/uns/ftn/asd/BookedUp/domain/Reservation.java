package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.Date;

public class Reservation {
    private Long id;
    private Date start;
    private Date end;
    private Integer guestsNumber;

    private Long accommodationId;
    private Long guestId;

    private ReservationStatus status;


    public Reservation() {
    }

    public Reservation(Long id, Date start, Date end, Integer guestsNumber, Long accommodationId, Long guestId, ReservationStatus status) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.guestsNumber = guestsNumber;
        this.accommodationId = accommodationId;
        this.guestId = guestId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(Integer guestsNumber) {
        this.guestsNumber = guestsNumber;
    }


    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }



    public void copyValues(Reservation reservation) {
        this.id = reservation.getId();
        this.start = reservation.getStart();
        this.end = reservation.getEnd();
        this.guestsNumber = reservation.getGuestsNumber();
        this.accommodationId = reservation.getAccommodationId();
        this.guestId = reservation.getGuestId();
        this.status = reservation.getStatus();
    }
}
