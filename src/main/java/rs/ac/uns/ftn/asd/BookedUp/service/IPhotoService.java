package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;
import rs.ac.uns.ftn.asd.BookedUp.dto.PhotoDTO;

import java.util.Collection;

public interface IPhotoService {
    Collection<PhotoDTO> getAll();

    PhotoDTO getById(Long id);

    PhotoDTO create(PhotoDTO photoDTO) throws Exception;

    PhotoDTO update(PhotoDTO photoDTO) throws Exception;

    void delete(Long id);
}
