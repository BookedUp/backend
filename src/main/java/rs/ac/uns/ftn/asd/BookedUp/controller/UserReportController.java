package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;
import rs.ac.uns.ftn.asd.BookedUp.service.UserReportService;

import java.util.Collection;

@RestController
@RequestMapping("/api/user-reports")
public class UserReportController {
    @Autowired
    private UserReportService userReportService;

    /*url: /api/user-reports GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<UserReport>> getUserReports() {
        Collection<UserReport> userReports = userReportService.getAll();
        return new ResponseEntity<>(userReports, HttpStatus.OK);
    }

    /* url: /api/user-reports/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserReport> getUserReport(@PathVariable("id") Long id) {
        UserReport userReport = userReportService.getById(id);

        if (userReport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userReport, HttpStatus.OK);
    }

    /*url: /api/user-reports POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserReport> createUserReport(@RequestBody UserReport userReport) throws Exception {
        UserReport savedUserReport = userReportService.create(userReport);
        return new ResponseEntity<>(savedUserReport, HttpStatus.CREATED);
    }

    /* url: /api/user-reports/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserReport> updateUserReport(@RequestBody UserReport userReport, @PathVariable Long id)
            throws Exception {
        UserReport userReportForUpdate = userReportService.getById(id);
        userReportForUpdate.copyValues(userReport);

        UserReport updatedUserReport = userReportService.update(userReportForUpdate);

        if (updatedUserReport == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedUserReport, HttpStatus.OK);
    }

    /** url: /api/user-reports/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserReport> deleteUserReport(@PathVariable("id") Long id) {
        userReportService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}