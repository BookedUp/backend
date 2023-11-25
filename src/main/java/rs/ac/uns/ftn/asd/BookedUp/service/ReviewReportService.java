package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReviewReportRepository;

import java.util.Collection;

@Service
public class ReviewReportService implements IReviewReportService {
    @Autowired
    private IReviewReportRepository reviewReportRepository;

    @Override
    public Collection<ReviewReport> getAll() {
        return reviewReportRepository.getAll();
    }

    @Override
    public ReviewReport getById(Long id) {
        return reviewReportRepository.getById(id);
    }

    @Override
    public ReviewReport create(ReviewReport reviewReport) throws Exception {
        if (reviewReport.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        ReviewReport savedReviewReport = reviewReportRepository.create(reviewReport);
        return savedReviewReport;
    }

    @Override
    public ReviewReport update(ReviewReport reviewReport) throws Exception {
        ReviewReport reviewReportToUpdate = getById(reviewReport.getId());
        if (reviewReportToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        reviewReportToUpdate.setReason(reviewReport.getReason());
        reviewReportToUpdate.setReportedReview(reviewReport.getReportedReview());
        reviewReportToUpdate.setStatus(reviewReport.isStatus());

        ReviewReport updatedReviewReport = reviewReportRepository.update(reviewReportToUpdate);
        return updatedReviewReport;
    }

    @Override
    public void delete(Long id) {
        reviewReportRepository.delete(id);
    }
}
