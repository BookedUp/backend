package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Role;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.HostDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class HostMapper implements MapperInterface<Host, HostDTO>{

    AccommodationMapper accommodationMapper = new AccommodationMapper();

    ReservationMapper reservationMapper = new ReservationMapper();
    @Override
    public Host toEntity(HostDTO dto) {
        if (dto == null) {
            return null;
        }
        List<Accommodation> accommodations = new ArrayList<Accommodation>();
        if(dto.getProperties() != null) {
            for(AccommodationDTO accommodationDTO : dto.getProperties())
                accommodations.add(accommodationMapper.toEntity(accommodationDTO));
        }

        List<Reservation> reservations = new ArrayList<Reservation>();
        if(dto.getRequests() != null) {
            for(ReservationDTO reservationDTO : dto.getRequests())
                reservations.add(reservationMapper.toEntity(reservationDTO));
        }

        Host host = new Host();
        host.setId(dto.getId());
        host.setFirstName(dto.getFirstName());
        host.setLastName(dto.getLastName());
        host.setAddress(dto.getAddress());
        host.setPhone(dto.getPhone());
        host.setPassword(dto.getPassword());
        host.setEmail(dto.getEmail());
        host.setRole(Role.HOST);
        host.setBlocked(dto.isBlocked());
        host.setProperties(accommodations);
        host.setRequests(reservations);
        host.setNotifications(dto.getNotifications());
        host.setAverageRating(dto.getAverageRating());

        return host;

    }

    @Override
    public HostDTO toDto(Host entity) {
        if (entity == null) {
            return null;
        }
        List<AccommodationDTO> accommodationDTOS = new ArrayList<AccommodationDTO>();
        if(entity.getProperties() != null) {
            for(Accommodation accommodation : entity.getProperties())
                accommodationDTOS.add(accommodationMapper.toDto(accommodation));
        }

        List<ReservationDTO> reservationDTOS = new ArrayList<ReservationDTO>();
        if(entity.getRequests() != null) {
            for(Reservation reservation : entity.getRequests())
                reservationDTOS.add(reservationMapper.toDto(reservation));
        }

        HostDTO hostDto = new HostDTO();
        hostDto.setId(entity.getId());
        hostDto.setFirstName(entity.getFirstName());
        hostDto.setLastName(entity.getLastName());
        hostDto.setAddress(entity.getAddress());
        hostDto.setPhone(entity.getPhone());
        hostDto.setPassword(entity.getPassword());
        hostDto.setEmail(entity.getEmail());
        hostDto.setBlocked(entity.isBlocked());
        hostDto.setProperties(accommodationDTOS);
        hostDto.setRequests(reservationDTOS);
        hostDto.setNotifications(entity.getNotifications());
        hostDto.setAverageRating(entity.getAverageRating());

        return hostDto;

    }
}
