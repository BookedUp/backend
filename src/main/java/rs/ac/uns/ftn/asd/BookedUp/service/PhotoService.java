package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.dto.PhotoDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.PhotoMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.PhotoRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class PhotoService implements IPhotoService{
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private PhotoMapper photoMapper;
    @Override
    public Collection<PhotoDTO> getAll() {
        Collection<Photo> photos = (photoRepository.getAll());
        Collection<PhotoDTO> photoDTOS = new ArrayList<>();

        for (Photo photo : photos) {
            PhotoDTO photoDTO = photoMapper.toDto(photo);
            photoDTOS.add(photoDTO);
        }

        return photoDTOS;
    }

    @Override
    public PhotoDTO getById(Long id) {
        Photo photo = photoRepository.getById(id);
        return photoMapper.toDto(photo);
    }

    @Override
    public PhotoDTO create(PhotoDTO photoDTO) throws Exception {
        if (photoDTO.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Photo photo = photoMapper.toEntity(photoDTO);
        Photo savedPhoto = photoRepository.create(photo);
        return photoMapper.toDto(savedPhoto);
    }

    @Override
    public PhotoDTO update(PhotoDTO photoDTO) throws Exception {
        Photo photo = photoMapper.toEntity(photoDTO);
        Photo photoToUpdate = photoRepository.getById(photo.getId());
        if (photoToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        photoToUpdate.setUrl(photo.getUrl());
        photoToUpdate.setCaption(photo.getCaption());
        photoToUpdate.setWidth(photo.getWidth());
        photoToUpdate.setHeight(photo.getHeight());

        Photo updatedPhoto = photoRepository.create(photoToUpdate);
        return photoMapper.toDto(updatedPhoto);
    }

    @Override
    public void delete(Long id) {
        photoRepository.delete(id);
    }
}
