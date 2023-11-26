package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.time.LocalDateTime;

public class Review {

    private Long id;
    private User user;
    private int review;
    private String comment;
    private LocalDateTime date;
    private Host host;
    private Accommodation accommodation;
    private ReviewType type;
    private Boolean isReviewActive;

    public Review() {
    }

    public Review(Long id, User user, int review, String comment, LocalDateTime date, Host host, Accommodation accommodation, ReviewType type, Boolean isReviewActive) {
        this.id = id;
        this.user = user;
        this.review = review;
        this.comment = comment;
        this.date = date;
        this.host = host;
        this.accommodation = accommodation;
        this.type = type;
        this.isReviewActive = isReviewActive;
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
    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public int getReview() {return review;}

    public void setReview(int review) {this.review = review;}

    public LocalDateTime getDate() {return date;}

    public void setDate(LocalDateTime date) {this.date = date;}
    public Host getHost(){return host;}
    public void setHost(Host host){this.host = host;}
    public Accommodation getAccommodation(){return this.accommodation;}
    public void setAccommodation(Accommodation accommodation){this.accommodation = accommodation;}

    public ReviewType getType() {return type;}

    public void setType(ReviewType type) {this.type = type;}

    public Boolean getReviewActive() {return isReviewActive;}

    public void setReviewActive(Boolean reviewActive) {isReviewActive = reviewActive;}

    public void copyValues(Review review) {
        this.id = review.getId();
        this.user = review.getUser();
        this.review = review.getReview();
        this.comment = review.getComment();
        this.date = review.getDate();
        this.host = review.getHost();
        this.accommodation = review.getAccommodation();
        this.type = review.getType();
        this.isReviewActive = review.getReviewActive();
    }
}
