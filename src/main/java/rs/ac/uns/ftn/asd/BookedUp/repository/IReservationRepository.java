package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;

import java.util.Collection;

public interface IReservationRepository {
    Collection<Reservation> getAll();

    Reservation create(Reservation reservation);

    Reservation getById(Long id);

    Reservation update(Reservation reservation);

    void delete(Long id);
}
