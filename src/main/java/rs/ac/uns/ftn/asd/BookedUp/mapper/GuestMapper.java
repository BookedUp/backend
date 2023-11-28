package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.GuestDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.HostDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class GuestMapper implements MapperInterface<Guest, GuestDTO>{

    AccommodationMapper accommodationMapper = new AccommodationMapper();

    ReservationMapper reservationMapper = new ReservationMapper();
    @Override
    public Guest toEntity(GuestDTO dto) {
        if (dto == null) {
            return null;
        }
        List<Accommodation> favourites = new ArrayList<Accommodation>();
        if(dto.getFavourites() != null) {
            for(AccommodationDTO accommodationDTO : dto.getFavourites())
                favourites.add(accommodationMapper.toEntity(accommodationDTO));
        }

        List<Reservation> reservations = new ArrayList<Reservation>();
        if(dto.getReservations() != null) {
            for(ReservationDTO reservationDTO : dto.getReservations())
                reservations.add(reservationMapper.toEntity(reservationDTO));
        }

        List<Reservation> requests = new ArrayList<Reservation>();
        if(dto.getRequests() != null) {
            for(ReservationDTO reservationDTO : dto.getRequests())
                requests.add(reservationMapper.toEntity(reservationDTO));
        }

        Guest guest = new Guest();
        guest.setId(dto.getId());
        guest.setFirstName(dto.getFirstName());
        guest.setLastName(dto.getLastName());
        guest.setAddress(dto.getAddress());
        guest.setPhone(dto.getPhone());
        guest.setPassword(dto.getPassword());
        guest.setEmail(dto.getEmail());
        guest.setRole(Role.HOST);

        guest.setFavourites(favourites);
        guest.setRequests(requests);
        guest.setNotifications(dto.getNotifications());
        guest.setReservations(reservations);
        guest.setReviews(dto.getReviews());

        return guest;
    }

    @Override
    public GuestDTO toDto(Guest entity) {
        if (entity == null) {
            return null;
        }
        List<AccommodationDTO> favouritesDTOS = new ArrayList<AccommodationDTO>();
        if(entity.getFavourites() != null) {
            for(Accommodation accommodation : entity.getFavourites())
                favouritesDTOS.add(accommodationMapper.toDto(accommodation));
        }

        List<ReservationDTO> reservationDTOS = new ArrayList<ReservationDTO>();
        if(entity.getReservations() != null) {
            for(Reservation reservation : entity.getReservations())
                reservationDTOS.add(reservationMapper.toDto(reservation));
        }

        List<ReservationDTO> requestsDTOS = new ArrayList<ReservationDTO>();
        if(entity.getRequests() != null) {
            for(Reservation reservation : entity.getRequests())
                requestsDTOS.add(reservationMapper.toDto(reservation));
        }

        GuestDTO guestDto = new GuestDTO();
        guestDto.setId(entity.getId());
        guestDto.setFirstName(entity.getFirstName());
        guestDto.setLastName(entity.getLastName());
        guestDto.setAddress(entity.getAddress());
        guestDto.setPhone(entity.getPhone());
        guestDto.setPassword(entity.getPassword());
        guestDto.setEmail(entity.getEmail());

        guestDto.setFavourites(favouritesDTOS);
        guestDto.setRequests(requestsDTOS);
        guestDto.setNotifications(entity.getNotifications());
        guestDto.setReservations(reservationDTOS);
        guestDto.setReviews(guestDto.getReviews());

        return guestDto;

    }
}
