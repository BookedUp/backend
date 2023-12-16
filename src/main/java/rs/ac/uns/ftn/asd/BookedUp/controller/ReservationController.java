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
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReservationMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.ReservationService;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    /*url: /api/reservations GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> getReservations() {
        Collection<Reservation> reservations = reservationService.getAll();
        Collection<ReservationDTO> reservationsDTO = reservations.stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reservationsDTO, HttpStatus.OK);
    }

    //@PreAuthorize("hasAuthority('ROLE_HOST')")
    @GetMapping(value = "/host/{hostId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> getAllByHostId(@PathVariable("hostId") Long hostId) {
        Collection<Reservation> reservations = reservationService.findAllByHostId(hostId);
        Collection<ReservationDTO> reservationDTOS = reservations.stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/host/{hostId}/created", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> getAllCreatedByHostId(@PathVariable("hostId") Long hostId) {
        Collection<Reservation> reservations = reservationService.findAllCreatedByHostId(hostId);
        Collection<ReservationDTO> reservationDTOS = reservations.stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/host/{hostId}/accepted", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> getAllAcceptedByHostId(@PathVariable("hostId") Long hostId) {
        Collection<Reservation> reservations = reservationService.findAllAcceptedByHostId(hostId);
        Collection<ReservationDTO> reservationDTOS = reservations.stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/host/{hostId}/rejected", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> getAllRejectedByHostId(@PathVariable("hostId") Long hostId) {
        Collection<Reservation> reservations = reservationService.findAllRejectedByHostId(hostId);
        Collection<ReservationDTO> reservationDTOS = reservations.stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/host/{hostId}/completed", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> getAllCompletedByHostId(@PathVariable("hostId") Long hostId) {
        Collection<Reservation> reservations = reservationService.findAllCompletedByHostId(hostId);
        Collection<ReservationDTO> reservationDTOS = reservations.stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/host/{hostId}/cancelled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservationDTO>> getAllCancelledByHostId(@PathVariable("hostId") Long hostId) {
        Collection<Reservation> reservations = reservationService.findAllCancelledByHostId(hostId);
        Collection<ReservationDTO> reservationDTOS = reservations.stream()
                .map(ReservationMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    /* url: /api/reservations/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable("id") Long id) {
        Reservation reservation = reservationService.getById(id);

        if (reservation == null) {
            return new ResponseEntity<ReservationDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ReservationDTO>(ReservationMapper.toDto(reservation), HttpStatus.OK);
    }

    /*url: /api/reservations POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) throws Exception {
        Reservation createdReservation = null;

        try {
            createdReservation = reservationService.create(ReservationMapper.toEntity(reservationDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ReservationDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ReservationMapper.toDto(createdReservation), HttpStatus.CREATED);
    }



    /* url: /api/reservations/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> updateReservation(@Valid @RequestBody ReservationDTO reservationDTO, @PathVariable Long id)
            throws Exception {
        Reservation reservationForUpdate = reservationService.getById(id);
        if (reservationForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservationForUpdate.setStartDate(reservationDTO.getStartDate());
        reservationForUpdate.setEndDate(reservationDTO.getEndDate());
        reservationForUpdate.setGuestsNumber(reservationDTO.getGuestsNumber());
        reservationForUpdate.setAccommodation(AccommodationMapper.toEntity(reservationDTO.getAccommodation()));
        reservationForUpdate.setStatus(reservationDTO.getStatus());
        //dodati jos

        reservationForUpdate = reservationService.save(reservationForUpdate);

        return new ResponseEntity<ReservationDTO>(ReservationMapper.toDto(reservationForUpdate), HttpStatus.OK);
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
