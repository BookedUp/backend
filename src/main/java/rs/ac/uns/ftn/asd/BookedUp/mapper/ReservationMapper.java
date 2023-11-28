package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.ReservationService;

@Component
public class ReservationMapper implements MapperInterface<Reservation, ReservationDTO> {

    AccommodationMapper accommodationMapper = new AccommodationMapper();

    ReservationService reservationService = new ReservationService();

    @Override
    public Reservation toEntity(ReservationDTO dto) {
        if (dto == null) {
            return null;
        }

        Accommodation accommodation = accommodationMapper.toEntity(dto.getAccommodationDTO());

        Reservation reservation = new Reservation();

        reservation.setAccommodation(accommodation);
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        reservation.setGuestsNumber(dto.getGuestsNumber());
        reservation.setCreatedTime(null);
        reservation.setTotalPrice(0);
        reservation.setGuest(new Guest());
        reservation.setStatus(ReservationStatus.CREATED);

        return reservation;
    }

    @Override
    public ReservationDTO toDto(Reservation entity) {
        if (entity == null) {
            return null;
        }

        AccommodationDTO accommodationDTO = accommodationMapper.toDto(entity.getAccommodation());

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setAccommodationDTO(accommodationDTO);
        reservationDTO.setStartDate(entity.getStartDate());
        reservationDTO.setEndDate(entity.getEndDate());
        reservationDTO.setGuestsNumber(entity.getGuestsNumber());

        return reservationDTO;
    }
}
