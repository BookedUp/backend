package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.service.GuestService;

import java.util.Collection;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    /*url: /api/guests GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Guest>> getGuests() {
        Collection<Guest> guests = guestService.getAll();
        return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
    }

    /* url: /api/guests/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> getGuest(@PathVariable("id") Long id) {
        Guest guest = guestService.getById(id);

        if (guest == null) {
            return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Guest>(guest, HttpStatus.OK);
    }

    /*url: /api/guests POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) throws Exception {
        Guest savedGuest = guestService.create(guest);
        return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
    }

    /* url: /api/guests/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> updateGuest(@RequestBody Guest guest, @PathVariable Long id)
            throws Exception {
        Guest guestForUpdate = guestService.getById(id);
        guestForUpdate.copyValues(guest);

        Guest updatedGuest = guestService.update(guestForUpdate);

        if (updatedGuest == null) {
            return new ResponseEntity<Guest>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Guest>(updatedGuest, HttpStatus.OK);
    }

    /** url: /api/guests/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Guest> deleteGuest(@PathVariable("id") Long id) {
        guestService.delete(id);
        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
    }
}
