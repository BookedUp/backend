package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;
import rs.ac.uns.ftn.asd.BookedUp.service.ReviewReportService;

import java.util.Collection;

@RestController
@RequestMapping("/api/review-reports")
public class ReviewReportController {
    @Autowired
    private ReviewReportService reviewReportService;

    /*url: /api/review-reports GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReviewReport>> getReviewReports() {
        Collection<ReviewReport> reviewReports = reviewReportService.getAll();
        return new ResponseEntity<>(reviewReports, HttpStatus.OK);
    }

    /* url: /api/review-reports/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewReport> getReviewReport(@PathVariable("id") Long id) {
        ReviewReport reviewReport = reviewReportService.getById(id);

        if (reviewReport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reviewReport, HttpStatus.OK);
    }

    /*url: /api/review-reports POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewReport> createReviewReport(@RequestBody ReviewReport reviewReport) throws Exception {
        ReviewReport savedReviewReport = reviewReportService.create(reviewReport);
        return new ResponseEntity<>(savedReviewReport, HttpStatus.CREATED);
    }

    /* url: /api/review-reports/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewReport> updateReviewReport(@RequestBody ReviewReport reviewReport, @PathVariable Long id)
            throws Exception {
        ReviewReport reviewReportForUpdate = reviewReportService.getById(id);
        reviewReportForUpdate.copyValues(reviewReport);

        ReviewReport updatedReviewReport = reviewReportService.update(reviewReportForUpdate);

        if (updatedReviewReport == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedReviewReport, HttpStatus.OK);
    }

    /** url: /api/review-reports/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ReviewReport> deleteReviewReport(@PathVariable("id") Long id) {
        reviewReportService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
