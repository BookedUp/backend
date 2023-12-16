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
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.HostMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReviewMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.UserMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.ReviewService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    /*url: /api/reviews GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReviewDTO>> getReviews() {
        Collection<Review> reviews = reviewService.getAll();
        Collection<ReviewDTO> reviewsDTO = reviews.stream()
                .map(ReviewMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reviewsDTO, HttpStatus.OK);
    }

    /* url: /api/reviews/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> getReview(@PathVariable("id") Long id) {
        Review review = reviewService.getById(id);

        if (review == null) {
            return new ResponseEntity<ReviewDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ReviewDTO>(ReviewMapper.toDto(review), HttpStatus.OK);
    }

    /*url: /api/reviews POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) throws Exception {
        Review createdReview = null;

        try {
            createdReview = reviewService.create(ReviewMapper.toEntity(reviewDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ReviewDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ReviewMapper.toDto(createdReview), HttpStatus.CREATED);
    }



    /* url: /api/reviews/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewDTO> updateReview(@Valid @RequestBody ReviewDTO reviewDTO, @PathVariable Long id)
            throws Exception {
        Review reviewForUpdate = reviewService.getById(id);
        if (reviewForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //reviewForUpdate.setGuest(reviewDTO.getGuest());
        reviewForUpdate.setReview(reviewDTO.getReview());
        reviewForUpdate.setComment(reviewDTO.getComment());
        reviewForUpdate.setDate(reviewDTO.getDate());
        reviewForUpdate.setHost(HostMapper.toEntity(reviewDTO.getHostDTO()));
        reviewForUpdate.setAccommodation(AccommodationMapper.toEntity(reviewDTO.getAccommodationDTO()));
        reviewForUpdate.setType(reviewDTO.getType());

        reviewForUpdate = reviewService.save(reviewForUpdate);

        return new ResponseEntity<ReviewDTO>(ReviewMapper.toDto(reviewForUpdate), HttpStatus.OK);
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
