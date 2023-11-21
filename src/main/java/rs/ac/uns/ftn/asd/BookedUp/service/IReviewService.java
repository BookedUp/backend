package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Review;

import java.util.Collection;

public interface IReviewService {
    Collection<Review> getAll();

    Review getById(Long id);

    Review create(Review review) throws Exception;

    Review update(Review review) throws Exception;

    void delete(Long id);
}
