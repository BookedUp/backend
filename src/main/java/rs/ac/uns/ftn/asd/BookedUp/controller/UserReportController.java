package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewReportDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserReportDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.UserMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.UserReportMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.UserReportService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-reports")
@CrossOrigin
public class UserReportController {
    @Autowired
    private UserReportService userReportService;

    /*url: /api/user-reports GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<UserReportDTO>> getUserReports() {
        Collection<UserReport> userReports = userReportService.getAll();
        Collection<UserReportDTO> usersReportsDTO = userReports.stream()
                .map(UserReportMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(usersReportsDTO, HttpStatus.OK);
    }

    /* url: /api/user-reports/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserReportDTO> getUserReport(@PathVariable("id") Long id) {
        UserReport userReport = userReportService.getById(id);

        if (userReport == null) {
            return new ResponseEntity<UserReportDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UserReportDTO>(UserReportMapper.toDto(userReport), HttpStatus.OK);
    }

    /*url: /api/user-reports POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserReportDTO> createUserReport(@Valid @RequestBody UserReportDTO userReportDTO) throws Exception {
        UserReport createdUserReport = null;

        try {
            createdUserReport = userReportService.create(UserReportMapper.toEntity(userReportDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new UserReportDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(UserReportMapper.toDto(createdUserReport), HttpStatus.CREATED);
    }

    /* url: /api/user-reports/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserReportDTO> updateUserReport(@Valid @RequestBody UserReportDTO userReportDTO, @PathVariable Long id)
            throws Exception {
        UserReport userReportForUpdate = userReportService.getById(id);
        if (userReportForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userReportForUpdate.setReason(userReportDTO.getReason());
        userReportForUpdate.setReportedUser(UserMapper.toEntity(userReportDTO.getReportedUserDTO()));
        userReportForUpdate.setStatus(userReportDTO.isStatus());

        userReportForUpdate = userReportService.save(userReportForUpdate);

        return new ResponseEntity<UserReportDTO>(UserReportMapper.toDto(userReportForUpdate), HttpStatus.OK);
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

    /*url: /api/user-reports/reported-users GET*/
    @GetMapping(value = "/reported-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<UserDTO>> getAllReportedUsers() {
        Collection<User> reportedUsers = userReportService.getAllReportedUsers();
        Collection<UserDTO> reportedUsersDTO = reportedUsers.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reportedUsersDTO, HttpStatus.OK);
    }



}