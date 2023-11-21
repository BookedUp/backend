package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.Review;

import java.util.Collection;

public interface IReviewRepository {
    Collection<Review> getAll();

    Review create(Review review);

    Review getById(Long id);

    Review update(Review review);

    void delete(Long id);
}
