package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;

import java.util.Collection;

public interface IAccommodationRepository {
    Collection<Accommodation> getAll();
    Accommodation getById(Long id);
    Accommodation create(Accommodation accommodation) throws Exception;
    Accommodation update(Accommodation accommodation) throws Exception;
    void delete(Long id);
}
