package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;

import java.util.Collection;

public interface IAcommodationService {
    Collection<AccommodationDTO> getAll();
    AccommodationDTO getById(Long id);
    AccommodationDTO create(AccommodationDTO accommodationDto) throws Exception;
    AccommodationDTO update(AccommodationDTO accommodationDto) throws Exception;
    void delete(Long id);
}
