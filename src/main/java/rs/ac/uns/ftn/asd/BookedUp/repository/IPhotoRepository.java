package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;

import java.util.Collection;

public interface IPhotoRepository {
    Collection<Photo> getAll();
    Photo create(Photo photo);
    Photo getById(Long id);
    Photo update(Photo photo);
    void delete(Long id);
}
