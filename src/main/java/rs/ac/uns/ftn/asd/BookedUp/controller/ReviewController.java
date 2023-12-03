package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Review;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.ReviewService;

import java.util.Collection;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    /*url: /api/reviews GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReviewDTO>> getReviews() {
        Collection<ReviewDTO> reviewDTOS = reviewService.getAll();
        if (reviewDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<ReviewDTO>>(reviewDTOS, HttpStatus.OK);
    }

    /* url: /api/reviews/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> getReview(@PathVariable("id") Long id) {
        ReviewDTO reviewDTO = reviewService.getById(id);

        if (reviewDTO == null) {
            return new ResponseEntity<ReviewDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ReviewDTO>(reviewDTO, HttpStatus.OK);
    }

    /*url: /api/reviews POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) throws Exception {
        ReviewDTO createdReviewDto = null;

        try {
            createdReviewDto = reviewService.create(reviewDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ReviewDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdReviewDto, HttpStatus.CREATED);
    }



    /* url: /api/reviews/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> updateReview(@Valid @RequestBody ReviewDTO reviewDTO, @PathVariable Long id)
            throws Exception {
        ReviewDTO reviewForUpdate = reviewService.getById(id);
        if (reviewForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reviewForUpdate.copyValues(reviewDTO);
        ReviewDTO updatedReview = reviewService.update(reviewForUpdate);

        if (updatedReview == null) {
            return new ResponseEntity<ReviewDTO>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ReviewDTO>(updatedReview, HttpStatus.OK);
    }

    /** url: /api/reviews/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable("id") Long id) {
        try {
            reviewService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
    }

    private boolean validateReviewDTO(ReviewDTO reviewDTO) {
        return true;
    }
}
