package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
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
        if (userReportDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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
    public ResponseEntity<UserReportDTO> createUserReport(@Valid @RequestBody UserReportDTO userReportDTO) throws Exception {
        UserReportDTO createdUserReportDto = null;

        try {
            createdUserReportDto = userReportService.create(userReportDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new UserReportDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdUserReportDto, HttpStatus.CREATED);
    }

    private boolean validateUserReportDTO(UserReportDTO userReportDTO) {
        return true;
    }

    /* url: /api/user-reports/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserReportDTO> updateUserReport(@Valid @RequestBody UserReportDTO userReportDTO, @PathVariable Long id)
            throws Exception {
        UserReportDTO userReportForUpdate = userReportService.getById(id);
        if (userReportForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userReportForUpdate.copyValues(userReportDTO);
        UserReportDTO updatedUserReport = userReportService.update(userReportForUpdate);

        if (updatedUserReport == null) {
            return new ResponseEntity<UserReportDTO>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<UserReportDTO>(updatedUserReport, HttpStatus.OK);
    }

    /** url: /api/user-reports/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserReport> deleteUserReport(@PathVariable("id") Long id) {
        try {
            userReportService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}