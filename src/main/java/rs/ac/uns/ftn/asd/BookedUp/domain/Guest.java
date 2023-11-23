package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.List;

public class Guest extends User {
    private List<Long> reservationIds;
    private List<Long> reviewIds;
    private boolean isBlocked;
    private List<Long> favoriteAccommodationIds;

    public Guest() {
        super();
    }

    public Guest(
            Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, Role role,
            List<Long> reservationIds, List<Long> reviewIds,
            boolean isBlocked, List<Long> favoriteAccommodationIds) {
        super(id, firstName, lastName, address, phone, email, password, role);
        this.reservationIds = reservationIds;
        this.reviewIds = reviewIds;
        this.isBlocked = isBlocked;
        this.favoriteAccommodationIds = favoriteAccommodationIds;
    }

    public List<Long> getReservationIds() {
        return reservationIds;
    }

    public void setReservationIds(List<Long> reservationIds) {
        this.reservationIds = reservationIds;
    }

    public List<Long> getReviewIds() {
        return reviewIds;
    }

    public void setReviewIds(List<Long> reviewIds) {
        this.reviewIds = reviewIds;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public List<Long> getFavoriteAccommodationIds() {
        return favoriteAccommodationIds;
    }

    public void setFavoriteAccommodationIds(List<Long> favoriteAccommodationIds) {
        this.favoriteAccommodationIds = favoriteAccommodationIds;
    }

    public void copyValues(Guest guest) {
        super.copyValues(guest);
        this.reservationIds = guest.getReservationIds();
        this.reviewIds = guest.getReviewIds();
        this.isBlocked = guest.isBlocked();
        this.favoriteAccommodationIds = guest.getFavoriteAccommodationIds();
    }
}

