package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.NotificationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewReportDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.NotificationService;

import java.util.Collection;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    /*url: /api/notifications GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<NotificationDTO>> getNotifications() {
        Collection<NotificationDTO> notificationDTOS = notificationService.getAll();
        if (notificationDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<NotificationDTO>>(notificationDTOS, HttpStatus.OK);
    }

    /* url: /api/notifications/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationDTO> getNotification(@PathVariable("id") Long id) {
        NotificationDTO notificationDTO = notificationService.getById(id);

        if (notificationDTO == null) {
            return new ResponseEntity<NotificationDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<NotificationDTO>(notificationDTO, HttpStatus.OK);
    }

    /*url: /api/notifications POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) throws Exception {
        NotificationDTO createdNotificationDto = null;
        try {
            createdNotificationDto = notificationService.create(notificationDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new NotificationDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdNotificationDto, HttpStatus.CREATED);
    }

    private boolean validateNotificationDTO(NotificationDTO notificationDTO) {
        return true;
    }


    /* url: /api/notifications/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationDTO> updateNotification(@Valid @RequestBody NotificationDTO notificationDTO, @PathVariable Long id)
            throws Exception {
        NotificationDTO notificationForUpdate = notificationService.getById(id);
        //resurs za azuriranje nije pronadjen
        if (notificationForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        notificationForUpdate.copyValues(notificationDTO);
        NotificationDTO updatedNotification = notificationService.update(notificationForUpdate);

        if (updatedNotification == null) {
            return new ResponseEntity<NotificationDTO>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<NotificationDTO>(updatedNotification, HttpStatus.OK);
    }

    /** url: /api/notifications/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable("id") Long id) {
        try {
            notificationService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Notification>(HttpStatus.NO_CONTENT);
    }



}
