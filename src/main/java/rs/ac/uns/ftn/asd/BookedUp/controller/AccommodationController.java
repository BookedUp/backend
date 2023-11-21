package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationService;


import java.util.Collection;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    @Autowired
    private AccommodationService accommodationService;

    /*url: /api/accommodations GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Accommodation>> getAccommodations() {
        Collection<Accommodation> accommodations = accommodationService.getAll();
        return new ResponseEntity<Collection<Accommodation>>(accommodations, HttpStatus.OK);
    }

    /* url: /api/accommodations/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Accommodation> getAccommodation(@PathVariable("id") Long id) {
        Accommodation accommodation = accommodationService.getById(id);

        if (accommodation == null) {
            return new ResponseEntity<Accommodation>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Accommodation>(accommodation, HttpStatus.OK);
    }

    /*url: /api/accommodations POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Accommodation> createAccommodation(@RequestBody Accommodation accommodation) throws Exception {
        Accommodation savedAccommodation = accommodationService.create(accommodation);
        return new ResponseEntity<Accommodation>(savedAccommodation, HttpStatus.CREATED);
    }

    /* url: /api/accommodations/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Accommodation> updateAccommodation(@RequestBody Accommodation accommodation, @PathVariable Long id)
            throws Exception {
        Accommodation accommodationForUpdate = accommodationService.getById(id);
        accommodationForUpdate.copyValues(accommodation);

        Accommodation updatedAccommodation = accommodationService.update(accommodationForUpdate);

        if (updatedAccommodation == null) {
            return new ResponseEntity<Accommodation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Accommodation>(updatedAccommodation, HttpStatus.OK);
    }

    /** url: /api/accommodations/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Accommodation> deleteUser(@PathVariable("id") Long id) {
        accommodationService.delete(id);
        return new ResponseEntity<Accommodation>(HttpStatus.NO_CONTENT);
    }
}

