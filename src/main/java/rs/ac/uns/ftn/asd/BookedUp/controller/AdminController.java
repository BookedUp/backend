package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.*;
import rs.ac.uns.ftn.asd.BookedUp.mapper.*;
import rs.ac.uns.ftn.asd.BookedUp.service.AdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /*url: /api/admins GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AdminDTO>> getAdmins() {
        Collection<Admin> admins = adminService.getAll();
        Collection<AdminDTO> adminsDTO = admins.stream()
                .map(AdminMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(adminsDTO, HttpStatus.OK);
    }

    /* url: /api/admins/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable("id") Long id) {
        Admin admin = adminService.getById(id);

        if (admin == null) {
            return new ResponseEntity<AdminDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<AdminDTO>(AdminMapper.toDto(admin), HttpStatus.OK);
    }

    /*url: /api/admins POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody AdminDTO adminDTO) throws Exception {
        Admin createdAdmin = null;

        try {
            createdAdmin = adminService.create(AdminMapper.toEntity(adminDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AdminDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(AdminMapper.toDto(createdAdmin), HttpStatus.CREATED);
    }


    /* url: /api/admins/1 PUT*/
    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long id, @Valid @RequestBody AdminDTO adminDTO) throws Exception {
        Admin adminForUpdate = adminService.getById(id);
        if (adminForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        adminForUpdate.setFirstName(adminDTO.getFirstName());
        adminForUpdate.setLastName(adminDTO.getLastName());

        if (adminDTO.getAddress() != null) {
            adminForUpdate.setAddress(AddressMapper.toEntity(adminDTO.getAddress()));
        }
        adminForUpdate.setEmail(adminDTO.getEmail());
        adminForUpdate.setPassword(adminDTO.getPassword());
        adminForUpdate.setPhone(adminDTO.getPhone());
        adminForUpdate.setVerified(adminDTO.isVerified());
        adminForUpdate.setActive(adminDTO.isActive());

        if (adminDTO.getProfilePicture() != null) {
            adminForUpdate.setProfilePicture(PhotoMapper.toEntity(adminDTO.getProfilePicture()));
        }
        List<ReviewReport> reviewReports = new ArrayList<ReviewReport>();
        if(adminDTO.getReviewReports() != null) {
            for(ReviewReportDTO reviewReportDTO : adminDTO.getReviewReports())
                reviewReports.add(ReviewReportMapper.toEntity(reviewReportDTO));
        }

        List<UserReport> userReports = new ArrayList<UserReport>();
        if(adminDTO.getUserReports() != null) {
            for(UserReportDTO userReportDTO : adminDTO.getUserReports())
                userReports.add(UserReportMapper.toEntity(userReportDTO));
        }

        List<Accommodation> requests = new ArrayList<Accommodation>();
        if(adminDTO.getRequests() != null) {
            for(AccommodationDTO accommodationDTO : adminDTO.getRequests())
                requests.add(AccommodationMapper.toEntity(accommodationDTO));
        }

        List<Notification> notifications = new ArrayList<Notification>();

        adminForUpdate.setNotifications(notifications);
        adminForUpdate.setUserReports(userReports);
        adminForUpdate.setReviewReports(reviewReports);
        adminForUpdate.setRequests(requests);
        adminForUpdate = adminService.save(adminForUpdate);

        return new ResponseEntity<AdminDTO>(AdminMapper.toDto(adminForUpdate), HttpStatus.OK);

    }

    /* url: /api/admins/1/user-reports GET*/
//    @GetMapping("/{id}/user-reports")
//    public ResponseEntity<List<UserReportDTO>> getUserReports(@PathVariable Long id) {
//        try {
//            AdminDTO adminDTO = adminService.getById(id);
//
//            if (adminDTO == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            List<UserReportDTO> userReports = adminDTO.getUserReports();
//
//            if (userReports.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(userReports, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    /* url: /api/admins/1/user-reports/1/accept PUT*/
//    @PutMapping("/{id}/user-reports/{reportId}/accept")
//    public ResponseEntity<String> acceptUserReport(@PathVariable Long id, @PathVariable Long reportId) {
//        Admin admin = adminService.getById(id);
//        if (admin == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
//        }
//
//        // adminService.acceptUserReport(admin, reportId);
//
//        return ResponseEntity.ok("User report with id: " + reportId + " accepted successfully");
//    }
//
//    /* url: /api/admins/1/user-reports/1/reject PUT*/
//    @PutMapping("/{id}/user-reports/{reportId}/reject")
//    public ResponseEntity<String> rejectUserReport(@PathVariable Long id, @PathVariable Long reportId) {
//        Admin admin = adminService.getById(id);
//        if (admin == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
//        }
//
//        // adminService.rejectUserReport(admin, reportId);
//
//        return ResponseEntity.ok("User report with id: " + reportId + " rejected successfully");
//    }

    /* url: /api/admins/1/user-reports GET*/
//    @GetMapping(value = "/{id}/review-reports", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ReviewReportDTO>> getReviewReports(@PathVariable Long id) {
//        try {
//            AdminDTO adminDTO = adminService.getById(id);
//
//            if (adminDTO == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            List<ReviewReportDTO> reviewReports = adminDTO.getReviewReports();
//
//            if (reviewReports.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(reviewReports, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    /* url: /api/admins/1/user-reports/1/accept PUT*/
//    @PutMapping("/{id}/review-reports/{reportId}/accept")
//    public ResponseEntity<String> acceptReviewReports(@PathVariable Long id, @PathVariable Long reportId) {
//        Admin admin = adminService.getById(id);
//        if (admin == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
//        }
//
//        // adminService.acceptReviewReport(admin, reportId);
//
//        return ResponseEntity.ok("Review report with id: " + reportId + " accepted successfully");
//    }
//
//    /* url: /api/admins/1/user-reports/1/reject PUT*/
//    @PutMapping("/{id}/review-reports/{reportId}/reject")
//    public ResponseEntity<String> rejectReviewReports(@PathVariable Long id, @PathVariable Long reportId) {
//        Admin admin = adminService.getById(id);
//        if (admin == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
//        }
//
//        // adminService.rejectReviewReport(admin, reportId);
//
//        return ResponseEntity.ok("Review report with id: " + reportId + " rejected successfully");
//    }

    /* url: /api/admins/1/requests GET*/
//    @GetMapping(value = "/{id}/requests", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<AccommodationDTO>> getRequests(@PathVariable Long id) {
//        try {
//            AdminDTO adminDTO = adminService.getById(id);
//
//            if (adminDTO == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            List<AccommodationDTO> requests = adminDTO.getRequests();
//
//            if (requests.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(requests, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    /* url: /api/admins/1/requests/1/accept PUT*/
//    @PutMapping("/{id}/requests/{requestId}/accept")
//    public ResponseEntity<String> acceptRequests(@PathVariable Long id, @PathVariable Long requestId) {
//        Admin admin = adminService.getById(id);
//        if (admin == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
//        }
//
//        // adminService.acceptRequest(admin, reportId);
//
//        return ResponseEntity.ok("Request with id: " + requestId + " accepted successfully");
//    }
//
//    /* url: /api/admins/1/requests/1/reject PUT*/
//    @PutMapping("/{id}/requests/{requestId}/reject")
//    public ResponseEntity<String> rejectRequests(@PathVariable Long id, @PathVariable Long requestId) {
//        Admin admin = adminService.getById(id);
//        if (admin == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
//        }
//
//        // adminService.rejectRequest(admin, reportId);
//
//        return ResponseEntity.ok("Request with id: " + requestId + " rejected successfully");
//    }

    /** url: /api/admins/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable("id") Long id) {
        try {
            adminService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Admin>(HttpStatus.NO_CONTENT);
    }
}
