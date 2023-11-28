package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public Collection<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    @Override
    public Reservation getById(Long id) {
        return reservationRepository.getById(id);
    }

    @Override
    public Reservation create(Reservation reservation) throws Exception {
        if (reservation.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        return reservationRepository.create(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) throws Exception {
        Reservation reservationToUpdate = getById(reservation.getId());
        if (reservationToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        reservationToUpdate.setCreatedTime(reservation.getCreatedTime());
        reservationToUpdate.setStartDate(reservation.getStartDate());
        reservationToUpdate.setEndDate(reservation.getEndDate());
        reservationToUpdate.setTotalPrice(reservation.getTotalPrice());
        reservationToUpdate.setGuestsNumber(reservation.getGuestsNumber());
        reservationToUpdate.setAccommodation(reservation.getAccommodation());
        reservationToUpdate.setGuest(reservation.getGuest());
        reservationToUpdate.setStatus(reservation.getStatus());

        return reservationRepository.create(reservationToUpdate);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.delete(id);
    }

    @Override
    public List<Reservation> getOverlappingReservations(Reservation reservation) {
        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();

        List<Reservation> allReservations = new ArrayList<>(reservationRepository.getAll());

        List<Reservation> overlappingReservations = new ArrayList<>();

        for (Reservation existingReservation : allReservations) {
            if (!existingReservation.getId().equals(reservation.getId())) {
                Date existingStartDate = existingReservation.getStartDate();
                Date existingEndDate = existingReservation.getEndDate();

                boolean overlap = startDate.before(existingEndDate) && existingStartDate.before(endDate);

                if (overlap) {
                    overlappingReservations.add(existingReservation);
                }
            }
        }

        return overlappingReservations;
    }

    @Override
    public Reservation cancelReservation(Reservation reservation) throws Exception{
        Reservation reservationToUpdate = getById(reservation.getId());
        if (reservationToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        reservationToUpdate.setStatus(ReservationStatus.CANCELLED);
        return reservationRepository.create(reservationToUpdate);

    }
}
