package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;
import rs.ac.uns.ftn.asd.BookedUp.service.ReportService;

import java.util.Collection;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    /*url: /api/report GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Statistics>> getReport() {
        Collection<Statistics> report = reportService.getAll();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    /* url: /api/report/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Statistics> getReport(@PathVariable("id") Long id) {
        Statistics report = reportService.getById(id);

        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    /*url: /api/report POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Statistics> createReport(@RequestBody Statistics report) throws Exception {
        Statistics savedReport = reportService.create(report);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }

    /* url: /api/report/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Statistics> updateReport(@RequestBody Statistics report, @PathVariable Long id)
            throws Exception {
        Statistics reportForUpdate = reportService.getById(id);
        reportForUpdate.copyValues(report);

        Statistics updatedReport = reportService.update(reportForUpdate);

        if (updatedReport == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedReport, HttpStatus.OK);
    }

    /** url: /api/report/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Statistics> deleteReport(@PathVariable("id") Long id) {
        reportService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
