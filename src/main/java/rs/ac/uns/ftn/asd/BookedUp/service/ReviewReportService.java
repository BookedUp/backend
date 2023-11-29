package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Review;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewReportDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReviewMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReviewReportMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReviewReportRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ReviewReportService implements IReviewReportService {
    @Autowired
    private IReviewReportRepository reviewReportRepository;
    @Autowired
    private ReviewReportMapper reviewReportMapper;


    @Override
    public Collection<ReviewReportDTO> getAll() {
        Collection<ReviewReport> reviewReports = (reviewReportRepository.getAll());
        Collection<ReviewReportDTO> reviewReportDTOS = new ArrayList<>();

        for (ReviewReport reviewReport : reviewReports) {
            ReviewReportDTO reviewReportDTO = reviewReportMapper.toDto(reviewReport);
            reviewReportDTOS.add(reviewReportDTO);
        }

        return reviewReportDTOS;
    }

    @Override
    public ReviewReportDTO getById(Long id) {
        ReviewReport reviewReport =  reviewReportRepository.getById(id);
        return reviewReportMapper.toDto(reviewReport);
    }

    @Override
    public ReviewReportDTO create(ReviewReportDTO reviewReportDTO) throws Exception {
        if (reviewReportDTO.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        ReviewReport reviewReport = reviewReportMapper.toEntity(reviewReportDTO);
        ReviewReport createdReviewReport =  reviewReportRepository.create(reviewReport);
        return reviewReportMapper.toDto(createdReviewReport);
    }

    @Override
    public ReviewReportDTO update(ReviewReportDTO reviewReportDTO) throws Exception {
        ReviewReport reviewReport = reviewReportMapper.toEntity(reviewReportDTO);
        ReviewReport reviewReportToUpdate= reviewReportRepository.getById(reviewReport.getId());
        if (reviewReportToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        reviewReportToUpdate.setReason(reviewReport.getReason());
        reviewReportToUpdate.setReportedReview(reviewReport.getReportedReview());
        reviewReportToUpdate.setStatus(reviewReport.isStatus());

        ReviewReport updatedReviewReport = reviewReportRepository.update(reviewReportToUpdate);
        return reviewReportMapper.toDto(updatedReviewReport);
    }

    @Override
    public void delete(Long id) {
        reviewReportRepository.delete(id);
    }
}
