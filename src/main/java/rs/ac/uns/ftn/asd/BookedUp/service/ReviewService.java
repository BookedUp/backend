package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Review;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReviewMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;
    @Override
    public Collection<ReviewDTO> getAll() {
        Collection<Review> reviews = (reviewRepository.getAll());
        Collection<ReviewDTO> reviewDTOS = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDTO reviewDTO = reviewMapper.toDto(review);
            reviewDTOS.add(reviewDTO);
        }

        return reviewDTOS;
    }

    @Override
    public ReviewDTO getById(Long id) {
        Review review =  reviewRepository.getById(id);
        return reviewMapper.toDto(review);
    }

    @Override
    public ReviewDTO create(ReviewDTO reviewDTO) throws Exception {
        if (reviewDTO.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Review review = reviewMapper.toEntity(reviewDTO);
        Review createdReview =  reviewRepository.create(review);
        return reviewMapper.toDto(createdReview);
    }

    @Override
    public ReviewDTO update(ReviewDTO reviewDTO) throws Exception {
        Review review = reviewMapper.toEntity(reviewDTO);
        Review reviewToUpdate= reviewRepository.getById(review.getId());
        if (reviewToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }

        reviewToUpdate.setUser(review.getUser());
        reviewToUpdate.setReview(review.getReview());
        reviewToUpdate.setComment(review.getComment());
        reviewToUpdate.setDate(review.getDate());
        reviewToUpdate.setAccommodation(review.getAccommodation());
        reviewToUpdate.setHost(review.getHost());
        reviewToUpdate.setType(review.getType());
        reviewToUpdate.setReviewActive(review.getReviewActive());

        Review updatedReview = reviewRepository.create(reviewToUpdate);
        return reviewMapper.toDto(updatedReview);

    }

    @Override
    public void delete(Long id) {
        reviewRepository.delete(id);
    }
}
