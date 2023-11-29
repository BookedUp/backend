package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.dto.GuestDTO;

import java.util.Collection;

public interface IGuestService {
    Collection<GuestDTO> getAll();
    GuestDTO getById(Long id);
    GuestDTO create(GuestDTO guestDto) throws Exception;
    GuestDTO update(GuestDTO guestDto) throws Exception;
    void delete(Long id);
}
