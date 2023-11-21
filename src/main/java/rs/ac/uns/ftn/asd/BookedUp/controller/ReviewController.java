package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Review;
import rs.ac.uns.ftn.asd.BookedUp.service.ReviewService;

import java.util.Collection;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    /*url: /api/reviews GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Review>> getReviews() {
        Collection<Review> reviews = reviewService.getAll();
        return new ResponseEntity<Collection<Review>>(reviews, HttpStatus.OK);
    }

    /* url: /api/reviews/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> getReview(@PathVariable("id") Long id) {
        Review review = reviewService.getById(id);

        if (review == null) {
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    /*url: /api/reviews POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> createReview(@RequestBody Review review) throws Exception {
        Review savedReview = reviewService.create(review);
        return new ResponseEntity<Review>(savedReview, HttpStatus.CREATED);
    }

    /* url: /api/reviews/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> updateReview(@RequestBody Review review, @PathVariable Long id)
            throws Exception {
        Review reviewForUpdate = reviewService.getById(id);
        reviewForUpdate.copyValues(review);

        Review updatedReview = reviewService.update(reviewForUpdate);

        if (updatedReview == null) {
            return new ResponseEntity<Review>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Review>(updatedReview, HttpStatus.OK);
    }

    /** url: /api/reviews/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable("id") Long id) {
        reviewService.delete(id);
        return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
    }
}
