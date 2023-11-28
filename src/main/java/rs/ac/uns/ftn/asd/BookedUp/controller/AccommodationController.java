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
    public ResponseEntity<Collection<AccommodationDTO>> getAccommodations() {
        Collection<AccommodationDTO> accommodationDTOS = accommodationService.getAll();
        return new ResponseEntity<Collection<AccommodationDTO>>(accommodationDTOS, HttpStatus.OK);
    }

    /* url: /api/accommodations/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("id") Long id) {
        AccommodationDTO accommodationDto = accommodationService.getById(id);

        if (accommodationDto == null) {
            return new ResponseEntity<AccommodationDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<AccommodationDTO>(accommodationDto, HttpStatus.OK);
    }

    /*url: /api/accommodations POST*/
    @PreAuthorize("hasRole('HOST')")
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<AccommodationDTO> createAccommodation(@Valid @RequestBody AccommodationDTO accommodationDTO) throws Exception {
        AccommodationDTO createdAccommodationDTO = null;

        if(!this.validateCreateAccommodationDTO(accommodationDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
           createdAccommodationDTO = accommodationService.create(accommodationDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AccommodationDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdAccommodationDTO, HttpStatus.OK);
    }

    private boolean validateCreateAccommodationDTO(AccommodationDTO accommodationDTO) {
        return true;
    }

    /* url: /api/accommodations/1 PUT*/
    @PreAuthorize("hasRole('HOST')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody AccommodationDTO accommodationDto, @PathVariable Long id)
            throws Exception {
        AccommodationDTO accommodationForUpdate = accommodationService.getById(id);
        accommodationForUpdate.copyValues(accommodationDto);

        AccommodationDTO updatedAccommodation = accommodationService.update(accommodationForUpdate);

        if (updatedAccommodation == null) {
            return new ResponseEntity<AccommodationDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<AccommodationDTO>(updatedAccommodation, HttpStatus.OK);
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
            AccommodationDTO accommodationDto = accommodationService.getById(id);
            AccommodationDTO approvedAccommodationDto = null;

            if (accommodationDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            accommodationDto.setStatus(AccommodationStatus.ACTIVE);

            approvedAccommodationDto = accommodationService.update(accommodationDto);

            return new ResponseEntity<>(approvedAccommodationDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/reject")
    public ResponseEntity<AccommodationDTO> rejectAccommodation(@PathVariable Long id) {
        try {
            AccommodationDTO accommodationDto = accommodationService.getById(id);
            AccommodationDTO rejectedAccommodationDto = null;

            if (accommodationDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            accommodationDto.setStatus(AccommodationStatus.REJECTED);

            rejectedAccommodationDto = accommodationService.update(accommodationDto);

            return new ResponseEntity<>(rejectedAccommodationDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasRole('HOST')")
    @RequestMapping(value = "/{id}/confirmation", method = RequestMethod.PUT)
    public ResponseEntity<AccommodationDTO> updateConfirmationType(
            @PathVariable Long id,
            @RequestParam Boolean automaticConfirmation) {

        try {
            AccommodationDTO accommodationDto = accommodationService.getById(id);
            AccommodationDTO updatedAccommodationDto = null;

            if (accommodationDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            accommodationDto.setAutomaticReservationAcceptance(automaticConfirmation);
            updatedAccommodationDto = accommodationService.update(accommodationDto);

            return new ResponseEntity<>(updatedAccommodationDto, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}

