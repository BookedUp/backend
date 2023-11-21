package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationReport;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationReportService;

import java.util.Collection;

@RestController
@RequestMapping("/api/accommodation-report")
public class AccommodationReportController {
    @Autowired
    private AccommodationReportService reportService;

    /*url: /api/accommodation-report GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AccommodationReport>> getReport() {
        Collection<AccommodationReport> report = reportService.getAll();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    /* url: /api/accommodation-report/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationReport> getReport(@PathVariable("id") Long id) {
        AccommodationReport report = reportService.getById(id);

        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    /*url: /api/accommodation-report POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationReport> createReport(@RequestBody AccommodationReport report) throws Exception {
        AccommodationReport savedReport = reportService.create(report);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }

    /* url: /api/accommodation-report/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationReport> updateReport(@RequestBody AccommodationReport report, @PathVariable Long id)
            throws Exception {
        AccommodationReport reportForUpdate = reportService.getById(id);
        reportForUpdate.copyValues(report);

        AccommodationReport updatedReport = reportService.update(reportForUpdate);

        if (updatedReport == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedReport, HttpStatus.OK);
    }

    /** url: /api/accommodation-report/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AccommodationReport> deleteReport(@PathVariable("id") Long id) {
        reportService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
