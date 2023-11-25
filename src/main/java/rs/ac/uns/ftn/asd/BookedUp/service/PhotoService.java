package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;
import rs.ac.uns.ftn.asd.BookedUp.repository.PhotoRepository;

import java.util.Collection;

@Service
public class PhotoService implements IPhotoService{
    @Autowired
    private PhotoRepository photoRepository;
    @Override
    public Collection<Photo> getAll() {
        return photoRepository.getAll();
    }

    @Override
    public Photo getById(Long id) {
        return photoRepository.getById(id);
    }

    @Override
    public Photo create(Photo notification) throws Exception {
        if (notification.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        return photoRepository.create(notification);
    }

    @Override
    public Photo update(Photo photo) throws Exception {
        Photo photoToUpdate = getById(photo.getId());
        if (photoToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        photoToUpdate.setUrl(photo.getUrl());
        photoToUpdate.setCaption(photo.getCaption());
        photoToUpdate.setWidth(photo.getWidth());
        photoToUpdate.setHeight(photo.getHeight());

        return photoRepository.create(photoToUpdate);
    }

    @Override
    public void delete(Long id) {
        photoRepository.delete(id);
    }
}
