package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;

import java.util.Collection;

public interface IGuestRepository {
    Collection<Guest> getAll();
    Guest getById(Long id);
    Guest create(Guest guest) throws Exception;
    Guest update(Guest guest) throws Exception;
    void delete(Long id);
}
