package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewReportDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.ReviewReportService;

import java.util.Collection;

@RestController
@RequestMapping("/api/review-reports")
public class ReviewReportController {
    @Autowired
    private ReviewReportService reviewReportService;

    /*url: /api/review-reports GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReviewReportDTO>> getReviewReports() {
        Collection<ReviewReportDTO> reviewReportDTOs = reviewReportService.getAll();
        return new ResponseEntity<Collection<ReviewReportDTO>>(reviewReportDTOs, HttpStatus.OK);
    }

    /* url: /api/review-reports/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewReportDTO> getReviewReport(@PathVariable("id") Long id) {
        ReviewReportDTO reviewReportDTO = reviewReportService.getById(id);

        if (reviewReportDTO == null) {
            return new ResponseEntity<ReviewReportDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ReviewReportDTO>(reviewReportDTO, HttpStatus.OK);
    }

    /*url: /api/review-reports POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewReportDTO> createReviewReport(@RequestBody ReviewReportDTO reviewReportDTO) throws Exception {
        ReviewReportDTO createdReviewReportDto = null;
        if(!this.validateReviewReportDTO(reviewReportDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            createdReviewReportDto = reviewReportService.create(reviewReportDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ReviewReportDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdReviewReportDto, HttpStatus.OK);
    }

    private boolean validateReviewReportDTO(ReviewReportDTO reviewReportDTO) {
        return true;
    }

    /* url: /api/review-reports/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewReportDTO> updateReviewReport(@RequestBody ReviewReportDTO reviewReportDTO, @PathVariable Long id)
            throws Exception {
        ReviewReportDTO reviewReportForUpdate = reviewReportService.getById(id);
        reviewReportForUpdate.copyValues(reviewReportDTO);

        ReviewReportDTO updatedReviewReport = reviewReportService.update(reviewReportForUpdate);

        if (updatedReviewReport == null) {
            return new ResponseEntity<ReviewReportDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ReviewReportDTO>(updatedReviewReport, HttpStatus.OK);
    }

    /** url: /api/review-reports/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ReviewReport> deleteReviewReport(@PathVariable("id") Long id) {
        reviewReportService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}/confirmation", method = RequestMethod.PUT)
    public ResponseEntity<ReviewReportDTO> updateConfirmation(
            @PathVariable Long id,
            @RequestParam Boolean confirmation) {

        try {
            ReviewReportDTO reviewReportDTO = reviewReportService.getById(id);
            ReviewReportDTO updatedReviewReportDTO = null;

            if (reviewReportDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            reviewReportDTO.setStatus(confirmation);
            updatedReviewReportDTO = reviewReportService.update(reviewReportDTO);

            return new ResponseEntity<>(updatedReviewReportDTO, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
