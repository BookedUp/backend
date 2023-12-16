package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.PhotoDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.PhotoMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.UserMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.PhotoService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/photo")
@CrossOrigin
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    /*url: /api/photo GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PhotoDTO>> getPhotos() {
        Collection<Photo> photos = photoService.getAll();
        Collection<PhotoDTO> photosDTO = photos.stream()
                .map(PhotoMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(photosDTO, HttpStatus.OK);
    }

    /* url: /api/photo/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoDTO> getPhoto(@PathVariable("id") Long id) {
        Photo photo = photoService.getById(id);

        if (photo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(PhotoMapper.toDto(photo), HttpStatus.OK);
    }

    /*url: /api/photo POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoDTO> createPhoto(@Valid @RequestBody PhotoDTO photoDTO) throws Exception {
        Photo createdPhoto = null;

        try {
            createdPhoto = photoService.create(PhotoMapper.toEntity(photoDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new PhotoDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(PhotoMapper.toDto(createdPhoto), HttpStatus.CREATED);
    }

    /* url: /api/photo/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhotoDTO> updatePhoto(@RequestBody PhotoDTO photoDTO, @PathVariable Long id)
            throws Exception {
        Photo photoForUpdate = photoService.getById(id);

        if (photoForUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        photoForUpdate.setUrl(photoDTO.getUrl());
        photoForUpdate.setCaption(photoDTO.getCaption());

        photoForUpdate = photoService.save(photoForUpdate);

        return new ResponseEntity<>(PhotoMapper.toDto(photoForUpdate), HttpStatus.OK);
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
