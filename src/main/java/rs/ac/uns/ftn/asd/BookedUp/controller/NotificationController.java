package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;
import rs.ac.uns.ftn.asd.BookedUp.service.NotificationService;

import java.util.Collection;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    /*url: /api/notifications GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Notification>> getNotifications() {
        Collection<Notification> notifications = notificationService.getAll();
        return new ResponseEntity<Collection<Notification>>(notifications, HttpStatus.OK);
    }

    /* url: /api/notifications/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> getNotification(@PathVariable("id") Long id) {
        Notification notification = notificationService.getById(id);

        if (notification == null) {
            return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /*url: /api/notifications POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) throws Exception {
        Notification savedNotification = notificationService.create(notification);
        return new ResponseEntity<Notification>(savedNotification, HttpStatus.CREATED);
    }

    /* url: /api/notifications/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> updateNotification(@RequestBody Notification notification, @PathVariable Long id)
            throws Exception {
        Notification notificationForUpdate = notificationService.getById(id);
        notificationForUpdate.copyValues(notification);

        Notification updatedNotification = notificationService.update(notificationForUpdate);

        if (updatedNotification == null) {
            return new ResponseEntity<Notification>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Notification>(updatedNotification, HttpStatus.OK);
    }

    /** url: /api/notifications/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable("id") Long id) {
        notificationService.delete(id);
        return new ResponseEntity<Notification>(HttpStatus.NO_CONTENT);
    }
}
