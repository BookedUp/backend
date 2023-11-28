package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;

import java.util.Collection;

public interface IAcommodationService {
    Collection<Accommodation> getAll();
    Accommodation getById(Long id);
    Accommodation create(Accommodation accommodation) throws Exception;
    Accommodation update(Accommodation accommodation) throws Exception;
    void delete(Long id);

    void accept(Long id) throws Exception;

    void reject(Long id) throws Exception;
}
