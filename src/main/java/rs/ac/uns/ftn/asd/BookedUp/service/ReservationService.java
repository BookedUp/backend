package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.ReservationMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;
    @Override
    public Collection<ReservationDTO> getAll() {
        Collection<Reservation> reservations = (reservationRepository.getAll());
        Collection<ReservationDTO> reservationsDTO = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO = reservationMapper.toDto(reservation);
            reservationsDTO.add(reservationDTO);
        }

        return reservationsDTO;

    }

    @Override
    public ReservationDTO getById(Long id) {
        Reservation reservation =  reservationRepository.getById(id);
        return reservationMapper.toDto(reservation);
    }

    @Override
    public ReservationDTO create(ReservationDTO reservationDto) throws Exception {
        if (reservationDto.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Reservation reservation = reservationMapper.toEntity(reservationDto);
        Reservation createdReservation =  reservationRepository.create(reservation);
        return reservationMapper.toDto(createdReservation);
    }

    @Override
    public ReservationDTO update(ReservationDTO reservationDto) throws Exception {
        Reservation reservation = reservationMapper.toEntity(reservationDto);
        Reservation reservationToUpdate = reservationRepository.getById(reservation.getId());
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

        Reservation updatedReservation = reservationRepository.create(reservationToUpdate);
        return reservationMapper.toDto(updatedReservation);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.delete(id);
    }

    @Override
    public List<ReservationDTO> getOverlappingReservations(ReservationDTO reservationDto) {
        Reservation reservation = reservationMapper.toEntity(reservationDto);
        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();

        List<Reservation> allReservations = new ArrayList<>(reservationRepository.getAll());

        List<ReservationDTO> overlappingReservations = new ArrayList<>();

        for (Reservation existingReservation : allReservations) {
            if (!existingReservation.getId().equals(reservation.getId())) {
                Date existingStartDate = existingReservation.getStartDate();
                Date existingEndDate = existingReservation.getEndDate();

                boolean overlap = startDate.before(existingEndDate) && existingStartDate.before(endDate);

                if (overlap) {
                    overlappingReservations.add(reservationMapper.toDto(existingReservation));
                }
            }
        }

        return overlappingReservations;
    }

    @Override
    public ReservationDTO cancelReservation(ReservationDTO reservationDto) throws Exception{
        Reservation reservation = reservationMapper.toEntity(reservationDto);
        Reservation reservationToUpdate = reservationRepository.getById(reservation.getId());
        if (reservationToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        reservationToUpdate.setStatus(ReservationStatus.CANCELLED);
        Reservation updatedReservation = reservationRepository.create(reservationToUpdate);
        return reservationMapper.toDto(updatedReservation);

    }
}
