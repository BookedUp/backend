package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReservationMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationMapper reservationMapper;

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
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDto) throws Exception {
        Reservation reservation = null;

        if(!this.validateReservationDTO(reservationDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {

            reservation = reservationMapper.toEntity(reservationDto);
            reservation = reservationService.create(reservation);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ReservationDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reservationMapper.toDto(reservation), HttpStatus.OK);
    }

    private boolean validateReservationDTO(ReservationDTO reservationDto) {
        return true;
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
            Reservation reservation = reservationService.getById(id);

            if (reservation == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if (reservation.getAccommodation().isAutomaticReservationAcceptance()) {
                // Ako je aktivirana automatska potvrda, odmah potvrdi rezervaciju
                reservation.setStatus(ReservationStatus.ACCEPTED);
                reservationService.update(reservation);
                return new ResponseEntity<>(reservationMapper.toDto(reservation), HttpStatus.OK);
            } else {
                // Ako je aktivna ručna potvrda, vlasnik može prihvatiti ili odbiti rezervaciju
                if (confirmation) {
                    // Ako je zahtev prihvaćen, automatski odbij ostale zahteve koji se preklapaju
                    List<Reservation> overlappingReservations = reservationService.getOverlappingReservations(reservation);
                    for (Reservation overlappingReservation : overlappingReservations) {
                        if (!overlappingReservation.getId().equals(reservation.getId())) {
                            overlappingReservation.setStatus(ReservationStatus.REJECTED);
                            reservationService.update(overlappingReservation);
                        }
                    }

                    reservation.setStatus(ReservationStatus.ACCEPTED);
                } else {
                    reservation.setStatus(ReservationStatus.REJECTED);
                }

                reservationService.update(reservation);
                return new ResponseEntity<>(reservationMapper.toDto(reservation), HttpStatus.OK);
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
            Reservation reservation = reservationService.getById(id);

            if (reservation == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Guest guest = new Guest(); // Prilagoditi kako dobavljamo trenutnog gosta
            //implementirati proveru da li je moguce otkazati rezervaciju (cancellationDeadline)

            reservation = reservationService.cancelReservation(reservation);
            ReservationDTO canceledReservationDTO = reservationMapper.toDto(reservation);
            return new ResponseEntity<>(canceledReservationDTO, HttpStatus.OK);
//
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
