package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Review;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReviewRepository implements IReviewRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Review> reviews = new ConcurrentHashMap<Long, Review>();
    @Override
    public Collection<Review> getAll() {
        return this.reviews.values();
    }

    @Override
    public Review create(Review review) {
        Long id = review.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            review.setId(id);
        }

        this.reviews.put(id, review);
        return review;
    }

    @Override
    public Review getById(Long id) {
        return this.reviews.get(id);
    }

    @Override
    public Review update(Review review) {
        Long id = review.getId();

        if (id != null) {
            this.reviews.put(id, review);
        }

        return review;
    }

    @Override
    public void delete(Long id) {
        this.reviews.remove(id);
    }
}
