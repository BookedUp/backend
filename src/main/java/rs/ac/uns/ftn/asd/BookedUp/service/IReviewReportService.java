package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;

import java.util.Collection;

public interface IReviewReportService {
    Collection<ReviewReport> getAll();
    ReviewReport getById(Long id);
    ReviewReport create(ReviewReport reviewReport) throws Exception;
    ReviewReport update(ReviewReport reviewReport) throws Exception;
    void delete(Long id);
}
