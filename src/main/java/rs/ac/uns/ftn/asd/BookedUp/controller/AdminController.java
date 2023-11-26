package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Admin;
import rs.ac.uns.ftn.asd.BookedUp.service.AdminService;

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
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) throws Exception {
        Admin savedAdmin = adminService.create(admin);
        return new ResponseEntity<Admin>(savedAdmin, HttpStatus.CREATED);
    }

    /* url: /api/admins/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @PathVariable Long id)
            throws Exception {
        Admin adminForUpdate = adminService.getById(id);
        adminForUpdate.copyValues(admin);

        Admin updatedAdmin = adminService.update(adminForUpdate);

        if (updatedAdmin == null) {
            return new ResponseEntity<Admin>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Admin>(updatedAdmin, HttpStatus.OK);
    }

    /** url: /api/admins/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable("id") Long id) {
        adminService.delete(id);
        return new ResponseEntity<Admin>(HttpStatus.NO_CONTENT);
    }
}
