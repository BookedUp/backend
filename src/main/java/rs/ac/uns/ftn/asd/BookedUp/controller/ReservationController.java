package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
        if(!this.validateReservationDTO(reservationDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            createdReservationDto = reservationService.create(reservationDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ReservationDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdReservationDto, HttpStatus.OK);
    }

    private boolean validateReservationDTO(ReservationDTO reservationDto) {
        return true;
    }


    /* url: /api/reservations/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservationDto, @PathVariable Long id)
            throws Exception {
        ReservationDTO reservationForUpdate = reservationService.getById(id);
        reservationForUpdate.copyValues(reservationDto);

        ReservationDTO updatedReservation = reservationService.update(reservationForUpdate);

        if (updatedReservation == null) {
            return new ResponseEntity<ReservationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ReservationDTO>(updatedReservation, HttpStatus.OK);
    }

    /** url: /api/reservations/1 DELETE*/
    @PreAuthorize("hasRole('GUEST')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable("id") Long id) {
        reservationService.delete(id);
        return new ResponseEntity<Reservation>(HttpStatus.NO_CONTENT);
    }


    @PreAuthorize("hasRole('HOST')")
    @PutMapping("/{id}/confirmation")
    public ResponseEntity<ReservationDTO> confirmReservation(
            @PathVariable Long id,
            @RequestParam Boolean confirmation) {

        try {
            ReservationDTO reservationDto = reservationService.getById(id);
            ReservationDTO updatedReservationDto = null;


            if (reservationDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (reservationDto.getAccommodationDTO().isAutomaticReservationAcceptance()) {
                // Ako je aktivirana automatska potvrda, odmah potvrdi rezervaciju
                reservationDto.setStatus(ReservationStatus.ACCEPTED);

                updatedReservationDto = reservationService.update(reservationDto);
                return new ResponseEntity<>(updatedReservationDto, HttpStatus.OK);
            } else {
                // Ako je aktivna ručna potvrda, vlasnik može prihvatiti ili odbiti rezervaciju
                if (confirmation) {
                    // Ako je zahtev prihvaćen, automatski odbij ostale zahteve koji se preklapaju
                    List<ReservationDTO> overlappingReservations = reservationService.getOverlappingReservations(reservationDto);
                    for (ReservationDTO overlappingReservation : overlappingReservations) {
                        if (!overlappingReservation.getId().equals(reservationDto.getId())) {
                            overlappingReservation.setStatus(ReservationStatus.REJECTED);
                            reservationService.update(overlappingReservation);
                        }
                    }

                    reservationDto.setStatus(ReservationStatus.ACCEPTED);
                } else {
                    reservationDto.setStatus(ReservationStatus.REJECTED);
                }

                updatedReservationDto = reservationService.update(reservationDto);
                return new ResponseEntity<>(updatedReservationDto, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('GUEST')")
    @PutMapping("/{id}/cancel")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable Long id) {
        try {
            ReservationDTO reservationDto = reservationService.getById(id);
            ReservationDTO updatedReservationDto = null;

            if (reservationDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Guest guest = new Guest(); // Prilagoditi kako dobavljamo trenutnog gosta
            //implementirati proveru da li je moguce otkazati rezervaciju (cancellationDeadline)

            updatedReservationDto = reservationService.cancelReservation(reservationDto);
            return new ResponseEntity<>(updatedReservationDto, HttpStatus.OK);
//
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
