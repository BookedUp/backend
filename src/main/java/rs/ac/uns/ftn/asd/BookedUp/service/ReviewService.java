package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.Amenity;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReviewMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReviewRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ReviewService implements ServiceInterface<Review> {

    @Autowired
    private IReviewRepository repository;

    @Override
    public Collection<Review> getAll() {
        return repository.findAll();
    }

    @Override
    public Review getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Review create(Review review) throws Exception {
        if (review.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }

        review.setIsReviewActive(true);
        return  repository.save(review);
    }

    @Override
    public Review save(Review review) throws Exception {
        return repository.save(review);
    }

//    @Override
//    public ReviewDTO update(ReviewDTO reviewDTO) throws Exception {
//        Review review = reviewMapper.toEntity(reviewDTO);
//        Review reviewToUpdate= repository.findById(review.getId()).orElse(null);
//        if (reviewToUpdate == null) {
//            throw new Exception("Trazeni entitet nije pronadjen.");
//        }
//
//        //reviewToUpdate.setGuest(review.getGuest());
//        reviewToUpdate.setReview(review.getReview());
//        reviewToUpdate.setComment(review.getComment());
//        reviewToUpdate.setDate(review.getDate());
//        reviewToUpdate.setAccommodation(review.getAccommodation());
//        reviewToUpdate.setHost(review.getHost());
//        reviewToUpdate.setType(review.getType());
//        reviewToUpdate.setIsReviewActive(review.getIsReviewActive());
//
//        Review updatedReview = repository.save(reviewToUpdate);
//        return reviewMapper.toDto(updatedReview);
//
//    }

    @Override
    public void delete(Long id) throws Exception  {

        Review review = repository.findById(id).orElse(null);

        if(review == null){
            throw  new Exception("Review doesn't exist");
        }

        review.setIsReviewActive(false);
        repository.save(review);
    }

    public List<Review> findAllByAccommodationId(Long id) {
        return repository.findAllByAccommodationId(id);
    }
    public List<Review> findAllAccommodationReviewsByGuestId(Long guestId) {
        return repository.findAllAccommodationReviewsByGuestId(guestId);
    }

    public List<Review> findAllHostReviewsByGuestId(Long guestId) {
        return repository.findAllHostReviewsByGuestId(guestId);
    }

    public List<Review> findAllByGuestId(Long guestId) {
        return repository.findAllByGuestId(guestId);
    }
}
