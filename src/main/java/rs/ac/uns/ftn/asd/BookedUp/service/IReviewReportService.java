package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewReportDTO;

import java.util.Collection;

public interface IReviewReportService {
    Collection<ReviewReportDTO> getAll();
    ReviewReportDTO getById(Long id);
    ReviewReportDTO create(ReviewReportDTO reviewReportDTO) throws Exception;
    ReviewReportDTO update(ReviewReportDTO reviewReportDTO) throws Exception;
    void delete(Long id);
}
