package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.dto.*;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationService;
import rs.ac.uns.ftn.asd.BookedUp.service.HostService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    @Autowired
    private HostService hostService;

    @Autowired
    private AccommodationService accommodationService;

    /*url: /api/hosts GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<HostDTO>> getHosts() {
        Collection<HostDTO> hostDTOS = hostService.getAll();
        if (hostDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<HostDTO>>(hostDTOS, HttpStatus.OK);
    }

    /* url: /api/hosts/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HostDTO> getHost(@PathVariable("id") Long id) {
        HostDTO hostDto = hostService.getById(id);

        if (hostDto == null) {
            return new ResponseEntity<HostDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<HostDTO>(hostDto, HttpStatus.OK);
    }

    /*url: /api/hosts POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HostDTO> createHost(@RequestBody HostDTO hostDto) throws Exception {
        HostDTO createdHostDTO = null;

        try {
            createdHostDTO = hostService.create(hostDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new HostDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdHostDTO, HttpStatus.CREATED);
    }

    private boolean validateHostDTO(HostDTO hostDto) {
        return true;
    }

    /* url: /api/hosts/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HostDTO> updateHost(@Valid @RequestBody HostDTO hostDto, @PathVariable Long id)
            throws Exception {
        HostDTO hostForUpdate = hostService.getById(id);
        //resurs za azuriranje nije pronadjen
        if (hostForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hostForUpdate.copyValues(hostDto);
        HostDTO updatedHost = hostService.update(hostForUpdate);

        if (updatedHost == null) {
            return new ResponseEntity<HostDTO>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<HostDTO>(updatedHost, HttpStatus.OK);
    }

    /** url: /api/hosts/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Host> deleteHost(@PathVariable("id") Long id) {
        try {
           hostService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Host>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/accommodations")
    public ResponseEntity<List<AccommodationDTO>> getHostAccommodations(@PathVariable Long id) {
        try {
            HostDTO hostDto = hostService.getById(id);

            if (hostDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<AccommodationDTO> accommodations = hostDto.getAccommodations();

            if (accommodations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(accommodations, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationDTO>> getHostReservations(@PathVariable Long id) {
        try {
            HostDTO hostDto = hostService.getById(id);

            if (hostDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<ReservationDTO> requests = hostDto.getRequests();

            if (requests.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(requests, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/reservations/search")
    public ResponseEntity<?> searchReservations(
            @RequestParam(required = false) String accommodationName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(required = false) String status) {

        try {
            // Implementirajte logiku pretrage i filtriranja smeštaja koristeći AccommodationService
//            List<AccommodationDTO> filteredAccommodations = accommodationService.searchAndFilterAccommodations(
//                    location, guestsNumber, startDate, endDate, amenities, type, minPrice, maxPrice);

            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Host search completed successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}/statistics")
    public ResponseEntity<List<StatisticsDTO>> getStatistics(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

        try {
            HostDTO hostDto = hostService.getById(id);

            if (hostDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<StatisticsDTO> filteredStatistics = hostDto.getStatistics().stream()
                    .filter(statistics -> hostService.isWithinDateRange(statistics.getFromDate(), fromDate, toDate))
                    .collect(Collectors.toList());

            if (filteredStatistics.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(filteredStatistics, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}/{accommodation_id}/accommodation-statistics")
    public ResponseEntity<List<AccommodationStatisticsDTO>> getAccommodationStatistics(@PathVariable Long id, @PathVariable Long accommodation_id) {
        try {
            HostDTO hostDto = hostService.getById(id);
            AccommodationDTO accommodationDTO = accommodationService.getById(accommodation_id);

            if (hostDto == null) {
                System.out.println("Host not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (accommodationDTO == null){
                System.out.println("Accommodation not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<AccommodationStatisticsDTO> accommodationStatistics = hostDto.getAccommodationStatistics();
            if (accommodationStatistics.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(accommodationStatistics, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
