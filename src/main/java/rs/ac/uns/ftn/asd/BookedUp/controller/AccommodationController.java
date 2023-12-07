package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.*;
import rs.ac.uns.ftn.asd.BookedUp.enums.AccommodationStatus;
import rs.ac.uns.ftn.asd.BookedUp.enums.AccommodationType;
import rs.ac.uns.ftn.asd.BookedUp.enums.PriceType;
import rs.ac.uns.ftn.asd.BookedUp.mapper.*;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationService;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    @Autowired
    private AccommodationService accommodationService;

    /*url: /api/accommodations GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AccommodationDTO>> getAccommodations() {
        Collection<Accommodation> accommodations = accommodationService.getAll();
        Collection<AccommodationDTO> accommodationsDTO = accommodations.stream()
                .map(AccommodationMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(accommodationsDTO, HttpStatus.OK);
    }

    /* url: /api/accommodations/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("id") Long id) {
        Accommodation accommodation = accommodationService.getById(id);

        if (accommodation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<AccommodationDTO>(AccommodationMapper.toDto(accommodation), HttpStatus.OK);
    }

    /*url: /api/accommodations POST*/
    @PreAuthorize("hasRole('HOST')")
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<AccommodationDTO> createAccommodation(@Valid @RequestBody AccommodationDTO accommodationDTO) throws Exception {
        Accommodation createdAccommodation = null;

        try {
            createdAccommodation = accommodationService.create(AccommodationMapper.toEntity(accommodationDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AccommodationDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(AccommodationMapper.toDto(createdAccommodation), HttpStatus.CREATED);
    }

    /* url: /api/accommodations/1 PUT*/
    @PreAuthorize("hasRole('HOST')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationDTO> updateAccommodation(@Valid @RequestBody AccommodationDTO accommodationDTO, @PathVariable Long id)
            throws Exception {
        Accommodation accommodationForUpdate = accommodationService.getById(id);
        if (accommodationForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (accommodationService.hasActiveReservations(accommodationForUpdate.getId())){
            throw new Exception("Accommodation has active reservations and cannot be updated");
        }

        accommodationForUpdate.setName(accommodationDTO.getName());
        accommodationForUpdate.setDescription(accommodationDTO.getDescription());
        accommodationForUpdate.setAddress(AddressMapper.toEntity(accommodationDTO.getAddress()));
        accommodationForUpdate.setAmenities(accommodationDTO.getAmenities());
        List<Photo> photos = new ArrayList<Photo>();
        if (accommodationDTO.getPhotos() != null){
            for (PhotoDTO photoDTO : accommodationDTO.getPhotos())
                photos.add(PhotoMapper.toEntity(photoDTO));
        }
        accommodationForUpdate.setPhotos(photos);
        accommodationForUpdate.setMinGuests(accommodationDTO.getMinGuests());
        accommodationForUpdate.setMaxGuests(accommodationDTO.getMaxGuests());
        accommodationForUpdate.setType(accommodationDTO.getType());
        List<DateRange> availability = accommodationDTO.getAvailability().stream()
                .map(DateRangeMapper::toEntity)
                .collect(Collectors.toList());

        accommodationForUpdate.setAvailability(availability);
        accommodationForUpdate.setPriceType(accommodationDTO.getPriceType());
        List<PriceChange> priceChanges = new ArrayList<PriceChange>();
        if (accommodationDTO.getPriceChanges() != null){
            for (PriceChangeDTO dto : accommodationDTO.getPriceChanges())
                priceChanges.add(PriceChangeMapper.toEntity(dto));
        }
        accommodationForUpdate.setPriceChanges(priceChanges);
        accommodationForUpdate.setAutomaticReservationAcceptance(accommodationDTO.isAutomaticReservationAcceptance());
        accommodationForUpdate.setStatus(accommodationDTO.getStatus());
        accommodationForUpdate.setHost(HostMapper.toEntity(accommodationDTO.getHost()));
        accommodationForUpdate.setPrice(accommodationDTO.getPrice());
        accommodationForUpdate.setStatus(AccommodationStatus.CHANGED);
        accommodationForUpdate = accommodationService.save(accommodationForUpdate);

        return new ResponseEntity<AccommodationDTO>(AccommodationMapper.toDto(accommodationForUpdate), HttpStatus.OK);
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
    public ResponseEntity<List<AccommodationDTO>> searchAccommodations(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer guestsNumber,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
    ) {
        try {
            List<Accommodation> searchedAccommodations = accommodationService.searchAccommodations(
                    country, city, guestsNumber, startDate, endDate);

            List<AccommodationDTO> results = searchedAccommodations.stream()
                    .map(AccommodationMapper::toDto)
                    .collect(Collectors.toList());

            for (AccommodationDTO accommodationDTO : results){
                if (accommodationDTO.getPriceType().equals(PriceType.PER_NIGHT)) {
                    long differenceInMilliseconds = endDate.getTime() - startDate.getTime();
                    long daysBetween = TimeUnit.DAYS.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);
                    double totalPrice = accommodationService.calculateTotalPrice(AccommodationMapper.toEntity(accommodationDTO), startDate, daysBetween);
                    accommodationDTO.setTotalPrice(totalPrice);
                } else {
                    double totalPrice = accommodationService.calculateTotalPrice(AccommodationMapper.toEntity(accommodationDTO), startDate, (long)guestsNumber);
                    accommodationDTO.setTotalPrice(totalPrice);
                }
            }

            return new ResponseEntity<>(results, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}

