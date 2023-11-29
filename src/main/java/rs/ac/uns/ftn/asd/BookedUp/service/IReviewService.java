package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Review;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewDTO;

import java.util.Collection;

public interface IReviewService {
    Collection<ReviewDTO> getAll();

    ReviewDTO getById(Long id);

    ReviewDTO create(ReviewDTO reviewDTO) throws Exception;

    ReviewDTO update(ReviewDTO reviewDTO) throws Exception;

    void delete(Long id);
}
