package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewReportDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReviewReportMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReviewReportRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ReviewReportService implements ServiceInterface<ReviewReport> {
    @Autowired
    private IReviewReportRepository repository;
    @Override
    public Collection<ReviewReport> getAll() {
        return repository.findAll();
    }

    @Override
    public ReviewReport getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ReviewReport create(ReviewReport reviewReport) throws Exception {
        if (reviewReport.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        return repository.save(reviewReport);
    }

    @Override
    public ReviewReport save(ReviewReport reviewReport) throws Exception {
        return repository.save(reviewReport);
    }

//    @Override
//    public ReviewReportDTO update(ReviewReportDTO reviewReportDTO) throws Exception {
//        ReviewReport reviewReport = reviewReportMapper.toEntity(reviewReportDTO);
//        ReviewReport reviewReportToUpdate= repository.findById(reviewReport.getId()).orElse(null);
//        if (reviewReportToUpdate == null) {
//            throw new Exception("The requested entity was not found.");
//        }
//        reviewReportToUpdate.setReason(reviewReport.getReason());
//        reviewReportToUpdate.setReportedReview(reviewReport.getReportedReview());
//        reviewReportToUpdate.setStatus(reviewReport.isStatus());
//
//        ReviewReport updatedReviewReport = repository.save(reviewReportToUpdate);
//        return reviewReportMapper.toDto(updatedReviewReport);
//    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
