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
import rs.ac.uns.ftn.asd.BookedUp.dto.*;
import rs.ac.uns.ftn.asd.BookedUp.mapper.*;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationService;
import rs.ac.uns.ftn.asd.BookedUp.service.HostService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
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
        Collection<Host> hosts = hostService.getAll();
        Collection<HostDTO> hostsDTO = hosts.stream()
                .map(HostMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(hostsDTO, HttpStatus.OK);
    }

    /* url: /api/hosts/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HostDTO> getHost(@PathVariable("id") Long id) {
        Host host = hostService.getById(id);

        if (host == null) {
            return new ResponseEntity<HostDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<HostDTO>(HostMapper.toDto(host), HttpStatus.OK);
    }

    /*url: /api/hosts POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HostDTO> createHost(@RequestBody HostDTO hostDto) throws Exception {
        Host createdHost = null;

        try {
            createdHost = hostService.create(HostMapper.toEntity(hostDto));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new HostDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HostMapper.toDto(createdHost), HttpStatus.CREATED);
    }

    private boolean validateHostDTO(HostDTO hostDto) {
        return true;
    }

    /* url: /api/hosts/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HostDTO> updateHost(@Valid @RequestBody HostDTO hostDTO, @PathVariable Long id)
            throws Exception {
        Host hostForUpdate = hostService.getById(id);
        if (hostForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        hostForUpdate.setFirstName(hostDTO.getFirstName());
        hostForUpdate.setLastName(hostDTO.getLastName());
        hostForUpdate.setAddress(AddressMapper.toEntity(hostDTO.getAddress()));
        hostForUpdate.setEmail(hostDTO.getEmail());
        hostForUpdate.setPassword(hostDTO.getPassword());
        hostForUpdate.setPhone(hostDTO.getPhone());
        hostForUpdate.setVerified(hostDTO.isVerified());
        hostForUpdate.setProfilePicture(PhotoMapper.toEntity(hostDTO.getProfilePicture()));
        hostForUpdate.setBlocked(hostDTO.isBlocked());
        hostForUpdate.setAverageRating(hostDTO.getAverageRating());
        List<Accommodation> accommodations = new ArrayList<Accommodation>();
        if(hostDTO.getAccommodations() != null) {
            for(AccommodationDTO accommodationDTO : hostDTO.getAccommodations())
                accommodations.add(AccommodationMapper.toEntity(accommodationDTO));
        }

        List<Reservation> requests = new ArrayList<Reservation>();
        if(hostDTO.getRequests() != null) {
            for(ReservationDTO reservationDTO : hostDTO.getRequests())
                requests.add(ReservationMapper.toEntity(reservationDTO));
        }

        List<Notification> notifications = new ArrayList<Notification>();
        if(hostDTO.getNotifications() != null) {
            for(NotificationDTO notificationDTO : hostDTO.getNotifications())
                notifications.add(NotificationMapper.toEntity(notificationDTO));
        }
//
//        List<Statistics> statistics = new ArrayList<Statistics>();
//        if(dto.getStatistics() != null) {
//            for(StatisticsDTO statisticsDTO : dto.getStatistics())
//                statistics.add(statisticsMapper.toEntity(statisticsDTO));
//        }
//
//        List<AccommodationStatistics> accommodationStatistics = new ArrayList<AccommodationStatistics>();
//        if(dto.getAccommodationStatistics() != null) {
//            for(AccommodationStatisticsDTO accommodationStatisticsDTO : dto.getAccommodationStatistics())
//                accommodationStatistics.add(accommodationStatisticsMapper.toEntity(accommodationStatisticsDTO));
//        }

        hostForUpdate.setAccommodations(accommodations);
        hostForUpdate.setNotifications(notifications);
        hostForUpdate.setRequests(requests);
        hostForUpdate.setReservationCreatedNotificationEnabled(hostDTO.isReservationCreatedNotificationEnabled());
        hostForUpdate.setCancellationNotificationEnabled(hostDTO.isCancellationNotificationEnabled());
        hostForUpdate.setHostRatingNotificationEnabled(hostDTO.isHostRatingNotificationEnabled());
        hostForUpdate.setAccommodationRatingNotificationEnabled(hostDTO.isAccommodationRatingNotificationEnabled());

        hostForUpdate = hostService.save(hostForUpdate);

        return new ResponseEntity<HostDTO>(HostMapper.toDto(hostForUpdate), HttpStatus.OK);
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

//    @GetMapping("/{id}/accommodations")
//    public ResponseEntity<List<AccommodationDTO>> getHostAccommodations(@PathVariable Long id) {
//        try {
//            HostDTO hostDto = hostService.getById(id);
//
//            if (hostDto == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            List<AccommodationDTO> accommodations = hostDto.getAccommodations();
//
//            if (accommodations.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(accommodations, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping("/{id}/reservations")
//    public ResponseEntity<List<ReservationDTO>> getHostReservations(@PathVariable Long id) {
//        try {
//            HostDTO hostDto = hostService.getById(id);
//
//            if (hostDto == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            List<ReservationDTO> requests = hostDto.getRequests();
//
//            if (requests.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(requests, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

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


//    @GetMapping("/{id}/statistics")
//    public ResponseEntity<List<StatisticsDTO>> getStatistics(
//            @PathVariable Long id,
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
//
//        try {
//            HostDTO hostDto = hostService.getById(id);
//
//            if (hostDto == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            List<StatisticsDTO> filteredStatistics = hostDto.getStatistics().stream()
//                    .filter(statistics -> hostService.isWithinDateRange(statistics.getFromDate(), fromDate, toDate))
//                    .collect(Collectors.toList());
//
//            if (filteredStatistics.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(filteredStatistics, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }


//    @GetMapping("/{id}/{accommodation_id}/accommodation-statistics")
//    public ResponseEntity<List<AccommodationStatisticsDTO>> getAccommodationStatistics(@PathVariable Long id, @PathVariable Long accommodation_id) {
//        try {
//            HostDTO hostDto = hostService.getById(id);
//            AccommodationDTO accommodationDTO = accommodationService.getById(accommodation_id);
//
//            if (hostDto == null) {
//                System.out.println("Host not found");
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            if (accommodationDTO == null){
//                System.out.println("Accommodation not found");
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            List<AccommodationStatisticsDTO> accommodationStatistics = hostDto.getAccommodationStatistics();
//            if (accommodationStatistics.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(accommodationStatistics, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }


}
