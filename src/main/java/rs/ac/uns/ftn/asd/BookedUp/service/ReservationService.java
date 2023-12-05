package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReservationMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReservationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService implements ServiceInterface<Reservation> {
    @Autowired
    private IReservationRepository repository;
    @Override
    public Collection<Reservation> getAll() {
        return repository.findAll();
    }

    @Override
    public Reservation getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Reservation create(Reservation reservation) throws Exception {
        if (reservation.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        return repository.save(reservation);
    }

    @Override
    public Reservation save(Reservation reservation) throws Exception {
        return repository.save(reservation);
    }

//    @Override
//    public ReservationDTO update(ReservationDTO reservationDto) throws Exception {
//        Reservation reservation = reservationMapper.toEntity(reservationDto);
//        Reservation reservationToUpdate = repository.findById(reservation.getId()).orElse(null);
//        if (reservationToUpdate == null) {
//            throw new Exception("Trazeni entitet nije pronadjen.");
//        }
//        reservationToUpdate.setCreatedTime(reservation.getCreatedTime());
//        reservationToUpdate.setStartDate(reservation.getStartDate());
//        reservationToUpdate.setEndDate(reservation.getEndDate());
//        reservationToUpdate.setTotalPrice(reservation.getTotalPrice());
//        reservationToUpdate.setGuestsNumber(reservation.getGuestsNumber());
//        reservationToUpdate.setAccommodation(reservation.getAccommodation());
//        reservationToUpdate.setGuest(reservation.getGuest());
//        reservationToUpdate.setStatus(reservation.getStatus());
//
//        Reservation updatedReservation = repository.save(reservationToUpdate);
//        return reservationMapper.toDto(updatedReservation);
//    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ReservationDTO> getOverlappingReservations(ReservationDTO reservationDto) {
        Reservation reservation = ReservationMapper.toEntity(reservationDto);
        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();

        List<Reservation> allReservations = new ArrayList<>(repository.findAll());

        List<ReservationDTO> overlappingReservations = new ArrayList<>();

        for (Reservation existingReservation : allReservations) {
            if (!existingReservation.getId().equals(reservation.getId())) {
                Date existingStartDate = existingReservation.getStartDate();
                Date existingEndDate = existingReservation.getEndDate();

                boolean overlap = startDate.before(existingEndDate) && existingStartDate.before(endDate);

                if (overlap) {
                    overlappingReservations.add(ReservationMapper.toDto(existingReservation));
                }
            }
        }

        return overlappingReservations;
    }

    public ReservationDTO cancelReservation(ReservationDTO reservationDto) throws Exception{
        Reservation reservation = ReservationMapper.toEntity(reservationDto);
        Reservation reservationToUpdate = repository.findById(reservation.getId()).orElse(null);
        if (reservationToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        reservationToUpdate.setStatus(ReservationStatus.CANCELLED);
        Reservation updatedReservation = repository.save(reservationToUpdate);
        return ReservationMapper.toDto(updatedReservation);

    }
}
