package rs.ac.uns.ftn.asd.BookedUp.mapper;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AccommodationMapper  implements MapperInterface<Accommodation, AccommodationDTO> {


    @Override
    public Accommodation toEntity(AccommodationDTO dto) {
        if (dto == null){
            return null;
        }

        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Review> reviews = new ArrayList<Review>();


        return new Accommodation(dto.getId(), dto.getName(), dto.getDescription(), dto.getAddress(), 0.0, dto.getMinGuests(),  dto.getMaxGuests(), 0, dto.isAutomaticReservationAcceptance(), null, dto.getPriceType(),  dto.getType(), dto.getAmenities(), dto.getPhotos(), dto.getAvailability(), dto.getPriceChanges(), reservations, reviews, 0.0);

    }

    @Override
    public AccommodationDTO toDto(Accommodation entity) {
        if (entity == null){
            return null;
        }
        return new AccommodationDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getAddress(), entity.getAmenities(), entity.getPhotos(), entity.getMinGuests(), entity.getMaxGuests(), entity.getType(), entity.getAvailability(), entity.getPriceType(), entity.getPriceChanges(), entity.isAutomaticReservationAcceptance());
    }




}
