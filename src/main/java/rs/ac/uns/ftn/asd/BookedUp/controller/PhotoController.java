package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.PhotoDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.PhotoService;

import java.util.Collection;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    /*url: /api/photo GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PhotoDTO>> getPhotos() {
        Collection<PhotoDTO> photoDTOS = photoService.getAll();
        if (photoDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(photoDTOS, HttpStatus.OK);
    }

    /* url: /api/photo/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoDTO> getPhoto(@PathVariable("id") Long id) {
        PhotoDTO photoDTO = photoService.getById(id);

        if (photoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(photoDTO, HttpStatus.OK);
    }

    /*url: /api/photo POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoDTO> createPhoto(@Valid @RequestBody PhotoDTO photoDTO) throws Exception {
        PhotoDTO createdPhotoDTO = null;

        try {
            createdPhotoDTO = photoService.create(photoDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new PhotoDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdPhotoDTO, HttpStatus.CREATED);
    }

    /* url: /api/photo/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoDTO> updatePhoto(@RequestBody PhotoDTO photoDTO, @PathVariable Long id)
            throws Exception {
        PhotoDTO photoForUpdateDTO = photoService.getById(id);
        photoForUpdateDTO.copyValues(photoDTO);

        PhotoDTO updatedReportDTO = photoService.update(photoForUpdateDTO);

        if (updatedReportDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedReportDTO, HttpStatus.OK);
    }

    /** url: /api/photo/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Photo> deletePhoto(@PathVariable("id") Long id) {
        try {
            photoService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
