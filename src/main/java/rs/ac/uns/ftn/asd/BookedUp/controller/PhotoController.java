package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;
import rs.ac.uns.ftn.asd.BookedUp.service.PhotoService;

import java.util.Collection;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    /*url: /api/photo GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Photo>> getPhoto() {
        Collection<Photo> photo = photoService.getAll();
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    /* url: /api/photo/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Photo> getPhoto(@PathVariable("id") Long id) {
        Photo photo = photoService.getById(id);

        if (photo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    /*url: /api/photo POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo) throws Exception {
        Photo savedPhoto = photoService.create(photo);
        return new ResponseEntity<>(savedPhoto, HttpStatus.CREATED);
    }

    /* url: /api/photo/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Photo> updatePhoto(@RequestBody Photo photo, @PathVariable Long id)
            throws Exception {
        Photo photoForUpdate = photoService.getById(id);
        photoForUpdate.copyValues(photo);

        Photo updatedReport = photoService.update(photoForUpdate);

        if (updatedReport == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedReport, HttpStatus.OK);
    }

    /** url: /api/photo/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Photo> deletePhoto(@PathVariable("id") Long id) {
        photoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
