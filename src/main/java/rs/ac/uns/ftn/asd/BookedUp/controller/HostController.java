package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.HostDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.HostService;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {
    @Autowired
    private HostService hostService;

    /*url: /api/hosts GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<HostDTO>> getHosts() {
        Collection<HostDTO> hostDTOS = hostService.getAll();
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

        if(!this.validateHostDTO(hostDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            createdHostDTO = hostService.create(hostDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new HostDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdHostDTO, HttpStatus.OK);
    }

    private boolean validateHostDTO(HostDTO hostDto) {
        return true;
    }

    /* url: /api/hosts/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HostDTO> updateHost(@RequestBody HostDTO hostDto, @PathVariable Long id)
            throws Exception {
        HostDTO hostForUpdate = hostService.getById(id);
        hostForUpdate.copyValues(hostDto);

        HostDTO updatedHost = hostService.update(hostForUpdate);

        if (updatedHost == null) {
            return new ResponseEntity<HostDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<HostDTO>(updatedHost, HttpStatus.OK);
    }

    /** url: /api/hosts/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Host> deleteHost(@PathVariable("id") Long id) {
        hostService.delete(id);
        return new ResponseEntity<Host>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/accommodations")
    public ResponseEntity<List<AccommodationDTO>> getHostAccommodations(@PathVariable Long id) {
        try {
            HostDTO hostDto = hostService.getById(id);

            if (hostDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(hostDto.getProperties(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationDTO>> getHostReservations(@PathVariable Long id) {
        try {
            HostDTO hostDto = hostService.getById(id);

            if (hostDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(hostDto.getRequests(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
