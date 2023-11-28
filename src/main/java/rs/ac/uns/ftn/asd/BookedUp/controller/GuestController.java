package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDetailedInDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.GuestService;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    /*url: /api/guests GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Guest>> getGuests() {
        Collection<Guest> guests = guestService.getAll();
        return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
    }

    /* url: /api/guests/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> getGuest(@PathVariable("id") Long id) {
        Guest guest = guestService.getById(id);

        if (guest == null) {
            return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Guest>(guest, HttpStatus.OK);
    }

    /*url: /api/guests POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) throws Exception {
        Guest savedGuest = guestService.create(guest);
        return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
    }

    /* url: /api/guests/1 PUT*/
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAdmin(@PathVariable Long id, @RequestBody UserDetailedInDTO adminDTO) throws Exception {
        // Retrieve admin information based on ID
        Guest guest = guestService.getById(id);

        if (guest == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guest not found");
        }

        // Update admin information
        guestService.updateGuestInformation(guest, adminDTO);

        return ResponseEntity.ok("Guest information updated");
    }

    /** url: /api/guests/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Guest> deleteGuest(@PathVariable("id") Long id) {
        guestService.delete(id);
        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
    }

    /** RESERVATIONS */
    /*url: /api/guest/1/reservations GET*/
    @GetMapping(value = "/{id}/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Reservation>> getReservations(@PathVariable("id") Long id) {
        Collection<Reservation> reservations = guestService.getById(id).getReservations();
        return new ResponseEntity<Collection<Reservation>>(reservations, HttpStatus.OK);
    }

    /*url: /api/guests/1/reservations POST*/
    @PostMapping(value = "/{id}/reservations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> createReservation(@RequestBody Reservation reservation, @PathVariable("id") Long id) throws Exception {
        Guest guestForUpdate = guestService.getById(id);
        guestForUpdate.addReservation(reservation);
        Guest savedGuest = guestService.update(guestForUpdate);
        return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
    }

    /* url: /api/guests/1/reservations PUT*/
    @PutMapping(value = "/{id}/reservations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> updateReservations(@RequestBody Reservation reservation, @PathVariable Long id)
            throws Exception {
        Guest guestForUpdate = guestService.getById(id);
        for (Reservation r : guestForUpdate.getReservations()) {
            if (Objects.equals(r.getId(), reservation.getId())) {
                guestForUpdate.getReservations().remove(r);
                guestForUpdate.addReservation(reservation);
            }
        }

        Guest updatedGuest = guestService.update(guestForUpdate);

        if (updatedGuest == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedGuest, HttpStatus.OK);
    }

    /** url: /api/guests/1/reservations/1 DELETE*/
    @DeleteMapping(value = "/{id}/reservations/{reservationsId}")
    public ResponseEntity<Guest> deleteReservations(@PathVariable("id") Long id, @PathVariable("reservationsId") Long reservationsId) throws Exception {

        Guest guestForUpdate = guestService.getById(id);
        guestForUpdate.getReservations().removeIf(r -> r.getId() == reservationsId);
        guestService.update(guestForUpdate);
        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
    }

    /** REVIEWS */
    /*url: /api/guest/1/reviews GET*/
    @GetMapping(value = "/{id}/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Review>> getReviews(@PathVariable("id") Long id) {
        Collection<Review> reviews = guestService.getById(id).getReviews();
        return new ResponseEntity<Collection<Review>>(reviews, HttpStatus.OK);
    }

    /*url: /api/guests/1/reviews POST*/
    @PostMapping(value = "/{id}/reviews", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> createReview(@RequestBody Review review, @PathVariable("id") Long id) throws Exception {
        Guest guestForUpdate = guestService.getById(id);
        guestForUpdate.addReview(review);
        Guest savedGuest = guestService.update(guestForUpdate);
        return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
    }

    /* url: /api/guests/1/reviews PUT*/
    @PutMapping(value = "/{id}/review", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> updateReview(@RequestBody Review review, @PathVariable Long id)
            throws Exception {
        Guest guestForUpdate = guestService.getById(id);
        for (Review r : guestForUpdate.getReviews()) {
            if (Objects.equals(r.getId(), review.getId())) {
                guestForUpdate.getReviews().remove(r);
                guestForUpdate.addReview(review);
            }
        }

        Guest updatedGuest = guestService.update(guestForUpdate);

        if (updatedGuest == null) {
            return new ResponseEntity<Guest>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Guest>(updatedGuest, HttpStatus.OK);
    }

    /** url: /api/guests/1/reviews/1 DELETE*/
    @DeleteMapping(value = "/{id}/review/{reviewId}")
    public ResponseEntity<Guest> deleteReview(@PathVariable("id") Long id, @PathVariable("reviewId") Long reviewId) throws Exception {

        Guest guestForUpdate = guestService.getById(id);
        guestForUpdate.getReviews().removeIf(r -> r.getId() == reviewId);
        guestService.update(guestForUpdate);
        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
    }

    /** REQUESTS */
    /*url: /api/guest/1/requests GET*/
    @GetMapping(value = "/{id}/requests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Reservation>> getRequests(@PathVariable("id") Long id) {
        Collection<Reservation> requests = guestService.getById(id).getRequests();
        return new ResponseEntity<Collection<Reservation>>(requests, HttpStatus.OK);
    }

    /** NOTIFICATIONS */
    /*url: /api/guest/1/notifications GET*/
    @GetMapping(value = "/{id}/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Notification>> getNotifications(@PathVariable("id") Long id) {
        Collection<Notification> notifications = guestService.getById(id).getNotifications();
        return new ResponseEntity<Collection<Notification>>(notifications, HttpStatus.OK);
    }

    /** FAVOURITES */
    /*url: /api/guest/1/favourites GET*/
    @GetMapping(value = "/{id}/favourites", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Accommodation>> getFavourites(@PathVariable("id") Long id) {
        Collection<Accommodation> favourites = guestService.getById(id).getFavourites();
        return new ResponseEntity<Collection<Accommodation>>(favourites, HttpStatus.OK);
    }

    /*url: /api/guests/1/favourites POST*/
    @PostMapping(value = "/{id}/favourites", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guest> createFavourite(@RequestBody Accommodation accommodation, @PathVariable("id") Long id) throws Exception {
        Guest guestForUpdate = guestService.getById(id);
        guestForUpdate.addFavourite(accommodation);
        Guest savedGuest = guestService.update(guestForUpdate);
        return new ResponseEntity<Guest>(savedGuest, HttpStatus.CREATED);
    }

    /** url: /api/guests/1/favourites/1 DELETE*/
    @DeleteMapping(value = "/{id}/favourites/{favouritesId}")
    public ResponseEntity<Guest> deleteFavourite(@PathVariable("id") Long id, @PathVariable("favouritesId") Long favouritesId) throws Exception {

        Guest guestForUpdate = guestService.getById(id);
        guestForUpdate.getFavourites().removeIf(r -> Objects.equals(r.getId(), favouritesId));
        guestService.update(guestForUpdate);
        return new ResponseEntity<Guest>(HttpStatus.NO_CONTENT);
    }


}
