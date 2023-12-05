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
import rs.ac.uns.ftn.asd.BookedUp.service.GuestService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    /*url: /api/guests GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<GuestDTO>> getGuests() {
        Collection<Guest> guests = guestService.getAll();
        Collection<GuestDTO> guestsDTO = guests.stream()
                .map(GuestMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(guestsDTO, HttpStatus.OK);
    }

    /* url: /api/guests/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuestDTO> getGuest(@PathVariable("id") Long id) {
        Guest guest = guestService.getById(id);

        if (guest == null) {
            return new ResponseEntity<GuestDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<GuestDTO>(GuestMapper.toDto(guest), HttpStatus.OK);
    }

    /*url: /api/guests POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuestDTO> createGuest(@RequestBody GuestDTO guestDto) throws Exception {
        Guest guest = null;

        try {
            guest = guestService.create(GuestMapper.toEntity(guestDto));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GuestDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(GuestMapper.toDto(guest), HttpStatus.CREATED);
    }

    private boolean validateGuestDTO(GuestDTO guestDto) {
        return true;
    }

    /* url: /api/guests/1 PUT*/
    @PutMapping("/{id}")
    public ResponseEntity<GuestDTO> updateGuest(@PathVariable Long id, @Valid @RequestBody GuestDTO guestDTO) throws Exception {
        Guest guestForUpdate = guestService.getById(id);
        if (guestForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        guestForUpdate.setFirstName(guestDTO.getFirstName());
        guestForUpdate.setLastName(guestDTO.getLastName());
        guestForUpdate.setAddress(guestDTO.getAddress());
        guestForUpdate.setEmail(guestDTO.getEmail());
        guestForUpdate.setPassword(guestDTO.getPassword());
        guestForUpdate.setPhone(guestDTO.getPhone());
        guestForUpdate.setVerified(guestDTO.isVerified());
        guestForUpdate.setProfilePicture(PhotoMapper.toEntity(guestDTO.getProfilePicture()));
        guestForUpdate.setBlocked(guestDTO.isBlocked());

        List<Accommodation> favourites = new ArrayList<Accommodation>();
        if(guestDTO.getFavourites() != null) {
            for(AccommodationDTO accommodationDTO : guestDTO.getFavourites())
                favourites.add(AccommodationMapper.toEntity(accommodationDTO));
        }

        List<Reservation> reservations = new ArrayList<Reservation>();
        if(guestDTO.getReservations() != null) {
            for(ReservationDTO reservationDTO : guestDTO.getReservations())
                reservations.add(ReservationMapper.toEntity(reservationDTO));
        }

        List<Reservation> requests = new ArrayList<Reservation>();
        if(guestDTO.getRequests() != null) {
            for(ReservationDTO reservationDTO : guestDTO.getRequests())
                requests.add(ReservationMapper.toEntity(reservationDTO));
        }

        List<Review> reviews = new ArrayList<Review>();
        if(guestDTO.getReviews() != null) {
            for(ReviewDTO reviewDTO : guestDTO.getReviews())
                reviews.add(ReviewMapper.toEntity(reviewDTO));
        }

        List<Notification> notifications = new ArrayList<Notification>();
        if(guestDTO.getNotifications() != null) {
            for(NotificationDTO notificationDTO : guestDTO.getNotifications())
                notifications.add(NotificationMapper.toEntity(notificationDTO));
        }
        guestForUpdate.setRequests(requests);
        guestForUpdate.setReservations(reservations);
        guestForUpdate.setFavourites(favourites);
        guestForUpdate.setReviews(reviews);
        guestForUpdate.setNotifications(notifications);
        guestForUpdate.setNotificationEnable(guestDTO.isNotificationEnable());

        guestForUpdate = guestService.save(guestForUpdate);

        return new ResponseEntity<GuestDTO>(GuestMapper.toDto(guestForUpdate), HttpStatus.OK);
    }

    /** url: /api/guests/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Guest> deleteGuest(@PathVariable("id") Long id) {
        try {
            guestService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
    }

    /** RESERVATIONS */
    /*url: /api/guest/1/reservations GET*/
//    @GetMapping(value = "/{id}/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ReservationDTO>> getReservations(@PathVariable Long id) {
//        try {
//            GuestDTO guestDto = guestService.getById(id);
//
//            if (guestDto == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            List<ReservationDTO> reservations = guestDto.getReservations();
//
//            if (reservations.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(reservations, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }




    /*url: /api/guests/1/reservations POST*/
//    @PostMapping(value = "/{id}/reservations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Guest> createReservation(@RequestBody Reservation reservation, @PathVariable("id") Long id) throws Exception {
//        Guest guestForUpdate = guestService.getById(id);
//        //guestForUpdate.addReservation(reservation);
//        Guest savedGuest = guestService.update(guestForUpdate);
//        return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
//    }

    /* url: /api/guests/1/reservations PUT*/
//    @PutMapping(value = "/{id}/reservations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Guest> updateReservations(@RequestBody Reservation reservation, @PathVariable Long id)
//            throws Exception {
//        Guest guestForUpdate = guestService.getById(id);
//        for (Reservation r : guestForUpdate.getReservations()) {
//            if (Objects.equals(r.getId(), reservation.getId())) {
//                guestForUpdate.getReservations().remove(r);
//                //guestForUpdate.addReservation(reservation);
//            }
//        }
//
//        Guest updatedGuest = guestService.update(guestForUpdate);
//
//        if (updatedGuest == null) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<>(updatedGuest, HttpStatus.OK);
//    }

    /** url: /api/guests/1/reservations/1 DELETE*/
//    @DeleteMapping(value = "/{id}/reservations/{reservationsId}")
//    public ResponseEntity<Guest> deleteReservations(@PathVariable("id") Long id, @PathVariable("reservationsId") Long reservationsId) throws Exception {
//
//        Guest guestForUpdate = guestService.getById(id);
//        guestForUpdate.getReservations().removeIf(r -> r.getId() == reservationsId);
//        guestService.update(guestForUpdate);
//        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
//    }

    /** REVIEWS */
    /*url: /api/guest/1/reviews GET*/
//    @GetMapping(value = "/{id}/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long id) {
//        try {
//            GuestDTO guestDto = guestService.getById(id);
//
//            if (guestDto == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            List<ReviewDTO> reviews = guestDto.getReviews();
//
//            if (reviews.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(reviews, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }


    /*url: /api/guests/1/reviews POST*/
//    @PostMapping(value = "/{id}/reviews", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Guest> createReview(@RequestBody Review review, @PathVariable("id") Long id) throws Exception {
//        Guest guestForUpdate = guestService.getById(id);
//        //guestForUpdate.addReview(review);
//        Guest savedGuest = guestService.update(guestForUpdate);
//        return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
//    }

    /* url: /api/guests/1/reviews PUT*/
//    @PutMapping(value = "/{id}/review", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Guest> updateReview(@RequestBody Review review, @PathVariable Long id)
//            throws Exception {
//        Guest guestForUpdate = guestService.getById(id);
//        for (Review r : guestForUpdate.getReviews()) {
//            if (Objects.equals(r.getId(), review.getId())) {
//                guestForUpdate.getReviews().remove(r);
//                //guestForUpdate.addReview(review);
//            }
//        }
//
//        Guest updatedGuest = guestService.update(guestForUpdate);
//
//        if (updatedGuest == null) {
//            return new ResponseEntity<Guest>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<Guest>(updatedGuest, HttpStatus.OK);
//    }

    /** url: /api/guests/1/reviews/1 DELETE*/
//    @DeleteMapping(value = "/{id}/review/{reviewId}")
//    public ResponseEntity<Guest> deleteReview(@PathVariable("id") Long id, @PathVariable("reviewId") Long reviewId) throws Exception {
//
//        Guest guestForUpdate = guestService.getById(id);
//        guestForUpdate.getReviews().removeIf(r -> r.getId() == reviewId);
//        guestService.update(guestForUpdate);
//        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
//    }

    /** REQUESTS */
    /*url: /api/guest/1/requests GET*/
//    @GetMapping(value = "/{id}/requests", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ReservationDTO>> getRequests(@PathVariable Long id) {
//        try {
//            GuestDTO guestDto = guestService.getById(id);
//
//            if (guestDto == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            List<ReservationDTO> requests = guestDto.getRequests();
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

    /** NOTIFICATIONS */
    /*url: /api/guest/1/notifications GET*/
//    @GetMapping(value = "/{id}/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Collection<NotificationDTO>> getNotifications(@PathVariable("id") Long id) {
//        try {
//            GuestDTO guestDto = guestService.getById(id);
//
//            if (guestDto == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            List<NotificationDTO> notifications = guestDto.getNotifications();
//
//            if (notifications.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(notifications, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    /** FAVOURITES */
    /*url: /api/guest/1/favourites GET*/
//    @GetMapping(value = "/{id}/favourites", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<AccommodationDTO>> getFavourites(@PathVariable Long id) {
//        try {
//            GuestDTO guestDto = guestService.getById(id);
//
//            if (guestDto == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            List<AccommodationDTO> favourites = guestDto.getFavourites();
//
//            if (favourites.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(favourites, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    /*url: /api/guests/1/favourites POST*/
//    @PostMapping(value = "/{id}/favourites", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<GuestDTO> createFavourite(@RequestBody AccommodationDTO accommodationDTO, @PathVariable("id") Long id) throws Exception {
//        GuestDTO guestForUpdate = guestService.getById(id);
//        //guestForUpdate.addFavourite(accommodation);
//        GuestDTO savedGuest = guestService.update(guestForUpdate);
//        return new ResponseEntity<GuestDTO>(savedGuest, HttpStatus.CREATED);
//    }
//
//    /** url: /api/guests/1/favourites/1 DELETE*/
//    @DeleteMapping(value = "/{id}/favourites/{favouritesId}")
//    public ResponseEntity<GuestDTO> deleteFavourite(@PathVariable("id") Long id, @PathVariable("favouritesId") Long favouritesId) throws Exception {
//
//        GuestDTO guestForUpdate = guestService.getById(id);
//        guestForUpdate.getFavourites().removeIf(r -> Objects.equals(r.getId(), favouritesId));
//        guestService.update(guestForUpdate);
//        return new ResponseEntity<GuestDTO>(HttpStatus.NO_CONTENT);
//    }

    @GetMapping("/{id}/requests/search")
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
                response.put("message", "Guest search completed successfully!");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }



}
