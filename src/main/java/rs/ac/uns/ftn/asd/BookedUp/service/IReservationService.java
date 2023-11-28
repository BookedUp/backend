package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;

import java.util.Collection;
import java.util.List;

public interface IReservationService {
    Collection<Reservation> getAll();

    Reservation getById(Long id);

    Reservation create(Reservation reservation) throws Exception;

    Reservation update(Reservation reservation) throws Exception;

    void delete(Long id);

    List<Reservation> getOverlappingReservations(Reservation reservation);

    Reservation cancelReservation(Reservation reservation) throws Exception;
}
