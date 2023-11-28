package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationService;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    @Autowired
    private AccommodationService accommodationService;

    @Autowired
    private AccommodationMapper accommodationMapper;

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
    @PreAuthorize("hasRole('HOST')")
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<AccommodationDTO> createAccommodation(@Valid @RequestBody AccommodationDTO createAccommodationDTO) throws Exception {
        Accommodation accommodation = null;

        if(!this.validateCreateAccommodationDTO(createAccommodationDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {

           accommodation = accommodationMapper.toEntity(createAccommodationDTO);
           accommodation = accommodationService.create(accommodation);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AccommodationDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(accommodationMapper.toDto(accommodation), HttpStatus.OK);
    }

    private boolean validateCreateAccommodationDTO(AccommodationDTO createAccommodationDTO) {
        return true;
    }

    /* url: /api/accommodations/1 PUT*/
    @PreAuthorize("hasRole('HOST')")
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
    public ResponseEntity<Accommodation> deleteAccommodation(@PathVariable("id") Long id) {
        accommodationService.delete(id);
        return new ResponseEntity<Accommodation>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/approve")
    public ResponseEntity<AccommodationDTO> approveAccommodation(@PathVariable Long id) {
        try {
            Accommodation accommodation = accommodationService.getById(id);

            if (accommodation == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            accommodation.setStatus(AccommodationStatus.ACTIVE);

            accommodation = accommodationService.update(accommodation);
            AccommodationDTO approvedAccommodationDTO = accommodationMapper.toDto(accommodation);

            return new ResponseEntity<>(approvedAccommodationDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/reject")
    public ResponseEntity<AccommodationDTO> rejectAccommodation(@PathVariable Long id) {
        try {
            Accommodation accommodation = accommodationService.getById(id);

            if (accommodation == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            accommodation.setStatus(AccommodationStatus.REJECTED);

            accommodation = accommodationService.update(accommodation);
            AccommodationDTO approvedAccommodationDTO = accommodationMapper.toDto(accommodation);

            return new ResponseEntity<>(approvedAccommodationDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasRole('HOST')")
    @RequestMapping(value = "/{id}/confirmation", method = RequestMethod.PUT)
    public ResponseEntity<AccommodationDTO> updateConfirmationType(
            @PathVariable Long id,
            @RequestParam Boolean manualConfirmation) {

        try {
            Accommodation accommodation = accommodationService.getById(id);

            if (accommodation == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            accommodation.setAutomaticReservationAcceptance(manualConfirmation);
            accommodation = accommodationService.update(accommodation);

            return new ResponseEntity<>(accommodationMapper.toDto(accommodation), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}

