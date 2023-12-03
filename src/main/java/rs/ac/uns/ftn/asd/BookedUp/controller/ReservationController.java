package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.service.ReservationService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    /*url: /api/reservations GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> getReservations() {
        Collection<ReservationDTO> reservationsDTO = reservationService.getAll();
        if (reservationsDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<ReservationDTO>>(reservationsDTO, HttpStatus.OK);
    }

    /* url: /api/reservations/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable("id") Long id) {
        ReservationDTO reservationDto = reservationService.getById(id);

        if (reservationDto == null) {
            return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ReservationDTO>(reservationDto, HttpStatus.OK);
    }

    /*url: /api/reservations POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDto) throws Exception {
        ReservationDTO createdReservationDto = null;

        try {
            createdReservationDto = reservationService.create(reservationDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ReservationDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdReservationDto, HttpStatus.CREATED);
    }

    private boolean validateReservationDTO(ReservationDTO reservationDto) {
        return true;
    }


    /* url: /api/reservations/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> updateReservation(@Valid @RequestBody ReservationDTO reservationDto, @PathVariable Long id)
            throws Exception {
        ReservationDTO reservationForUpdate = reservationService.getById(id);
        if (reservationForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservationForUpdate.copyValues(reservationDto);
        ReservationDTO updatedReservation = reservationService.update(reservationForUpdate);

        if (updatedReservation == null) {
            return new ResponseEntity<ReservationDTO>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ReservationDTO>(updatedReservation, HttpStatus.OK);
    }

    /** url: /api/reservations/1 DELETE*/
    @PreAuthorize("hasRole('GUEST')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable("id") Long id) {
        try {
            reservationService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Reservation>(HttpStatus.NO_CONTENT);
    }


}
