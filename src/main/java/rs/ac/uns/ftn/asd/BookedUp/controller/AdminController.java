package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDetailedInDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.AdminService;
import java.util.List;
import java.util.Collection;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /*url: /api/admins GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Admin>> getAdmins() {
        Collection<Admin> admins = adminService.getAll();
        return new ResponseEntity<Collection<Admin>>(admins, HttpStatus.OK);
    }

    /* url: /api/admins/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admin> getAdmin(@PathVariable("id") Long id) {
        Admin admin = adminService.getById(id);

        if (admin == null) {
            return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Admin>(admin, HttpStatus.OK);
    }

    /*url: /api/admins POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admin> createAdmin(@RequestBody UserDetailedInDTO userDTO) throws Exception {
        // Convert UserDTO to Admin
        Admin admin = new Admin();
        admin.copyValuesFromDTO(userDTO);

        // Save the admin
        Admin savedAdmin = adminService.create(admin);

        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }


    /* url: /api/admins/1 PUT*/
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAdmin(@PathVariable Long id, @RequestBody UserDetailedInDTO adminDTO) throws Exception {
        // Retrieve admin information based on ID
        Admin admin = adminService.getById(id);

        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }

        // Update admin information
        adminService.updateAdminInformation(admin, adminDTO);

        return ResponseEntity.ok("Admin information updated");
    }

    /* url: /api/admins/1/user-reports GET*/
    @GetMapping("/{id}/user-reports")
    public ResponseEntity<List<UserReport>> getUserReports(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<UserReport> userReports = admin.getUserReports();
        return ResponseEntity.ok(userReports);
    }

    /* url: /api/admins/1/user-reports/1/accept PUT*/
    @PutMapping("/{id}/user-reports/{reportId}/accept")
    public ResponseEntity<String> acceptUserReport(@PathVariable Long id, @PathVariable Long reportId) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }

        // adminService.acceptUserReport(admin, reportId);

        return ResponseEntity.ok("User report with id: " + reportId + " accepted successfully");
    }

    /* url: /api/admins/1/user-reports/1/reject PUT*/
    @PutMapping("/{id}/user-reports/{reportId}/reject")
    public ResponseEntity<String> rejectUserReport(@PathVariable Long id, @PathVariable Long reportId) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }

        // adminService.rejectUserReport(admin, reportId);

        return ResponseEntity.ok("User report with id: " + reportId + " rejected successfully");
    }

    /* url: /api/admins/1/user-reports GET*/
    @GetMapping("/{id}/review-reports")
    public ResponseEntity<List<ReviewReport>> getReviewReports(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<ReviewReport> reviewReports = admin.getReviewReports();
        return ResponseEntity.ok(reviewReports);
    }

    /* url: /api/admins/1/user-reports/1/accept PUT*/
    @PutMapping("/{id}/review-reports/{reportId}/accept")
    public ResponseEntity<String> acceptReviewReports(@PathVariable Long id, @PathVariable Long reportId) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }

        // adminService.acceptReviewReport(admin, reportId);

        return ResponseEntity.ok("Review report with id: " + reportId + " accepted successfully");
    }

    /* url: /api/admins/1/user-reports/1/reject PUT*/
    @PutMapping("/{id}/review-reports/{reportId}/reject")
    public ResponseEntity<String> rejectReviewReports(@PathVariable Long id, @PathVariable Long reportId) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }

        // adminService.rejectReviewReport(admin, reportId);

        return ResponseEntity.ok("Review report with id: " + reportId + " rejected successfully");
    }

    /* url: /api/admins/1/requests GET*/
    @GetMapping("/{id}/requests")
    public ResponseEntity<List<Accommodation>> getRequests(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Accommodation> requests = admin.getRequests();
        return ResponseEntity.ok(requests);
    }

    /* url: /api/admins/1/requests/1/accept PUT*/
    @PutMapping("/{id}/requests/{requestId}/accept")
    public ResponseEntity<String> acceptRequests(@PathVariable Long id, @PathVariable Long requestId) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }

        // adminService.acceptRequest(admin, reportId);

        return ResponseEntity.ok("Request with id: " + requestId + " accepted successfully");
    }

    /* url: /api/admins/1/requests/1/reject PUT*/
    @PutMapping("/{id}/requests/{requestId}/reject")
    public ResponseEntity<String> rejectRequests(@PathVariable Long id, @PathVariable Long requestId) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }

        // adminService.rejectRequest(admin, reportId);

        return ResponseEntity.ok("Request with id: " + requestId + " rejected successfully");
    }

    /** url: /api/admins/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable("id") Long id) {
        adminService.delete(id);
        return new ResponseEntity<Admin>(HttpStatus.NO_CONTENT);
    }
}
