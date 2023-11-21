package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.service.ReservationService;

import java.util.Collection;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    /*url: /api/reservations GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Reservation>> getReservation() {
        Collection<Reservation> reservations = reservationService.getAll();
        return new ResponseEntity<Collection<Reservation>>(reservations, HttpStatus.OK);
    }

    /* url: /api/reservations/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> getReservation(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.getById(id);

        if (reservation == null) {
            return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }

    /*url: /api/reservations POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) throws Exception {
        Reservation savedReservation = reservationService.create(reservation);
        return new ResponseEntity<Reservation>(savedReservation, HttpStatus.CREATED);
    }

    /* url: /api/reservations/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation, @PathVariable Long id)
            throws Exception {
        Reservation reservationForUpdate = reservationService.getById(id);
        reservationForUpdate.copyValues(reservation);

        Reservation updatedReservation = reservationService.update(reservationForUpdate);

        if (updatedReservation == null) {
            return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Reservation>(updatedReservation, HttpStatus.OK);
    }

    /** url: /api/reservations/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable("id") Long id) {
        reservationService.delete(id);
        return new ResponseEntity<Reservation>(HttpStatus.NO_CONTENT);
    }
}
