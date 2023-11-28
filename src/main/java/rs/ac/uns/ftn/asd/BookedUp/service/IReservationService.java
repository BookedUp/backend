package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;

import java.util.Collection;
import java.util.List;

public interface IReservationService {
    Collection<ReservationDTO> getAll();

    ReservationDTO getById(Long id);

    ReservationDTO create(ReservationDTO reservationDto) throws Exception;

    ReservationDTO update(ReservationDTO reservationDto) throws Exception;

    void delete(Long id);

    List<ReservationDTO> getOverlappingReservations(ReservationDTO reservationDto);

    ReservationDTO cancelReservation(ReservationDTO reservationDto) throws Exception;
}
