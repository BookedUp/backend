package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;

import java.util.Collection;

public interface IReviewReportRepository {
    Collection<ReviewReport> getAll();
    ReviewReport create(ReviewReport reviewReport);
    ReviewReport getById(Long id);
    ReviewReport update(ReviewReport reviewReport);
    void delete(Long id);
}
