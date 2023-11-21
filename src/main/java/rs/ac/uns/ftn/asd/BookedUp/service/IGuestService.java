package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;

import java.util.Collection;

public interface IGuestService {
    Collection<Guest> getAll();
    Guest getById(Long id);
    Guest create(Guest guest) throws Exception;
    Guest update(Guest guest) throws Exception;
    void delete(Long id);
}
