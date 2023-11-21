package rs.ac.uns.ftn.asd.BookedUp.domain;

public class Review {

    private Long id;
    private String comment;
    private Integer hostRating;
    private Integer accommodationRating;
    private Long guestId;

    private Long accommodationId;
    private Long hostId;

    private ReviewStatus status;

    public Review() {
    }

    public Review(Long id, String comment, Integer hostRating, Integer accommodationRating, Long guestId, Long accommodationId, Long hostId, ReviewStatus status) {
        this.id = id;
        this.comment = comment;
        this.hostRating = hostRating;
        this.accommodationRating = accommodationRating;
        this.guestId = guestId;
        this.accommodationId = accommodationId;
        this.hostId = hostId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getHostRating() {
        return hostRating;
    }

    public void setHostRating(Integer hostRating) {
        this.hostRating = hostRating;
    }

    public Integer getAccommodationRating() {
        return accommodationRating;
    }

    public void setAccommodationRating(Integer accommodationRating) {
        this.accommodationRating = accommodationRating;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    public void copyValues(Review review) {
        this.id = review.getId();
        this.guestId = review.getGuestId();
        this.comment = review.getComment();
        this.hostRating = review.getHostRating();
        this.hostId = review.getHostId();
        this.accommodationRating = review.getAccommodationRating();
        this.accommodationId = review.getAccommodationId();
        this.status = review.getStatus();
    }
}
