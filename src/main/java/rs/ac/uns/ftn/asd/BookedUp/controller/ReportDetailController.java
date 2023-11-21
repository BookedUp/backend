package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReportDetail;
import rs.ac.uns.ftn.asd.BookedUp.service.ReportDetailService;

import java.util.Collection;

@RestController
@RequestMapping("/api/report-details")
public class ReportDetailController {
    @Autowired
    private ReportDetailService reportDetailService;

    /*url: /api/report-details GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReportDetail>> getReportDetail() {
        Collection<ReportDetail> reportDetail = reportDetailService.getAll();
        return new ResponseEntity<>(reportDetail, HttpStatus.OK);
    }

    /* url: /api/report-details/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportDetail> getReportDetail(@PathVariable("id") Long id) {
        ReportDetail reportDetail = reportDetailService.getById(id);

        if (reportDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reportDetail, HttpStatus.OK);
    }

    /*url: /api/report-details POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportDetail> createReportDetail(@RequestBody ReportDetail reportDetail) throws Exception {
        ReportDetail savedReportDetail = reportDetailService.create(reportDetail);
        return new ResponseEntity<>(savedReportDetail, HttpStatus.CREATED);
    }

    /* url: /api/report-details/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportDetail> updateReportDetail(@RequestBody ReportDetail reportDetail, @PathVariable Long id)
            throws Exception {
        ReportDetail reportDetailForUpdate = reportDetailService.getById(id);
        reportDetailForUpdate.copyValues(reportDetail);

        ReportDetail updatedReportDetail = reportDetailService.update(reportDetailForUpdate);

        if (updatedReportDetail == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedReportDetail, HttpStatus.OK);
    }

    /** url: /api/report-details/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ReportDetail> deleteReportDetail(@PathVariable("id") Long id) {
        reportDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}