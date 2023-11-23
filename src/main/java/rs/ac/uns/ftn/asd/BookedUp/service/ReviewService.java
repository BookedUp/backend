package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Review;
import rs.ac.uns.ftn.asd.BookedUp.repository.ReviewRepository;

import java.util.Collection;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public Collection<Review> getAll() {
        return reviewRepository.getAll();
    }

    @Override
    public Review getById(Long id) {
        return reviewRepository.getById(id);
    }

    @Override
    public Review create(Review review) throws Exception {
        if (review.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        return reviewRepository.create(review);
    }

    @Override
    public Review update(Review review) throws Exception {
        Review reviewToUpdate = getById(review.getId());
        if (reviewToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        reviewToUpdate.setComment(review.getComment());
        reviewToUpdate.setHostRating(review.getHostRating());
        reviewToUpdate.setAccommodationRating(review.getAccommodationRating());
        reviewToUpdate.setGuestId(review.getGuestId());
        reviewToUpdate.setAccommodationId(review.getAccommodationId());
        reviewToUpdate.setHostId(review.getHostId());
        reviewToUpdate.setStatus(review.getStatus());

        return reviewRepository.create(reviewToUpdate);
    }

    @Override
    public void delete(Long id) {
        reviewRepository.delete(id);
    }
}
