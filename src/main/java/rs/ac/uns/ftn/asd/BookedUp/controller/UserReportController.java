package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewReportDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserReportDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.UserReportService;

import java.util.Collection;

@RestController
@RequestMapping("/api/user-reports")
public class UserReportController {
    @Autowired
    private UserReportService userReportService;

    /*url: /api/user-reports GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<UserReportDTO>> getUserReports() {
        Collection<UserReportDTO> userReportDTOS = userReportService.getAll();
        return new ResponseEntity<Collection<UserReportDTO>>(userReportDTOS, HttpStatus.OK);
    }

    /* url: /api/user-reports/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserReportDTO> getUserReport(@PathVariable("id") Long id) {
        UserReportDTO userReportDTO = userReportService.getById(id);

        if (userReportDTO == null) {
            return new ResponseEntity<UserReportDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UserReportDTO>(userReportDTO, HttpStatus.OK);
    }

    /*url: /api/user-reports POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserReportDTO> createUserReport(@RequestBody UserReportDTO userReportDTO) throws Exception {
        UserReportDTO createdUserReportDto = null;
        if(!this.validateUserReportDTO(userReportDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            createdUserReportDto = userReportService.create(userReportDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new UserReportDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdUserReportDto, HttpStatus.OK);
    }

    private boolean validateUserReportDTO(UserReportDTO userReportDTO) {
        return true;
    }

    /* url: /api/user-reports/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserReportDTO> updateUserReport(@RequestBody UserReportDTO userReportDTO, @PathVariable Long id)
            throws Exception {
        UserReportDTO userReportForUpdate = userReportService.getById(id);
        userReportForUpdate.copyValues(userReportDTO);

        UserReportDTO updatedUserReport = userReportService.update(userReportForUpdate);

        if (updatedUserReport == null) {
            return new ResponseEntity<UserReportDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UserReportDTO>(updatedUserReport, HttpStatus.OK);
    }

    /** url: /api/user-reports/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserReport> deleteUserReport(@PathVariable("id") Long id) {
        userReportService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}