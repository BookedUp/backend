package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.enums.AccommodationType;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationService;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    @Autowired
    private AccommodationService accommodationService;

    /*url: /api/accommodations GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AccommodationDTO>> getAccommodations() {
        Collection<AccommodationDTO> accommodationDTOS = accommodationService.getAll();
        if (accommodationDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<AccommodationDTO>>(accommodationDTOS, HttpStatus.OK);
    }

    /* url: /api/accommodations/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("id") Long id) {
        AccommodationDTO accommodationDto = accommodationService.getById(id);

        if (accommodationDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<AccommodationDTO>(accommodationDto, HttpStatus.OK);
    }

    /*url: /api/accommodations POST*/
    @PreAuthorize("hasRole('HOST')")
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<AccommodationDTO> createAccommodation(@Valid @RequestBody AccommodationDTO accommodationDTO) throws Exception {
        AccommodationDTO createdAccommodationDTO = null;

        try {
           createdAccommodationDTO = accommodationService.create(accommodationDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AccommodationDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdAccommodationDTO, HttpStatus.CREATED);
    }

    private boolean validateCreateAccommodationDTO(AccommodationDTO accommodationDTO) {
        return true;
    }

    /* url: /api/accommodations/1 PUT*/
    @PreAuthorize("hasRole('HOST')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationDTO> updateAccommodation(@Valid @RequestBody AccommodationDTO accommodationDto, @PathVariable Long id)
            throws Exception {
        AccommodationDTO accommodationForUpdate = accommodationService.getById(id);
        if (accommodationForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        accommodationForUpdate.copyValues(accommodationDto);
        AccommodationDTO updatedAccommodation = accommodationService.update(accommodationForUpdate);

        if (updatedAccommodation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<AccommodationDTO>(updatedAccommodation, HttpStatus.OK);
    }


    /** url: /api/accommodations/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Accommodation> deleteAccommodation(@PathVariable("id") Long id) {
        try {
            accommodationService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Accommodation>(HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchAccommodations(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer guestsNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(required = false) List<String> amenities,
            @RequestParam(required = false) AccommodationType type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        try {
            // Implementirajte logiku pretrage i filtriranja smeštaja koristeći AccommodationService
//            List<AccommodationDTO> filteredAccommodations = accommodationService.searchAndFilterAccommodations(
//                    location, guestsNumber, startDate, endDate, amenities, type, minPrice, maxPrice);

            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Search completed successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}

