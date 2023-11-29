package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.enums.ReviewType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    public void copyValues(Review review) {
        this.id = review.getId();
        this.user = review.getUser();
        this.review = review.getReview();
        this.comment = review.getComment();
        this.date = review.getDate();
        this.host = review.getHost();
        this.accommodation = review.getAccommodation();
        this.type = review.getType();
        this.isReviewActive = review.isReviewActive;
    }
}
