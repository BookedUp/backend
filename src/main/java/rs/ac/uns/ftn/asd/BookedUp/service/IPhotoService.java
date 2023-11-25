package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;

import java.util.Collection;

public interface IPhotoService {
    Collection<Photo> getAll();

    Photo getById(Long id);

    Photo create(Photo photo) throws Exception;

    Photo update(Photo photo) throws Exception;

    void delete(Long id);
}
