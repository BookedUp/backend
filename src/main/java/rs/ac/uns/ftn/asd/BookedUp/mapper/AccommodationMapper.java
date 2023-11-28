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
//        List<PriceChange> priceChangeList = new ArrayList<PriceChange>();
//        priceChangeList.add(new PriceChange(new Date(), 0.0));

        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Review> reviews = new ArrayList<Review>();


        return new Accommodation(dto.getId(), dto.getName(),dto.getAddress(), dto.getDescription(), dto.getAmenities(), dto.getPhotos(), dto.getMinGuests(), dto.getMaxGuests(), dto.getType(), dto.getAvailability(), 0.0, dto.getPriceType(), null, dto.getPriceChanges(), 0, dto.isAutomaticReservationAcceptance(), reservations,reviews, 0.0 );

    }

    @Override
    public AccommodationDTO toDto(Accommodation entity) {
        return new AccommodationDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getAddress(), entity.getAmenities(), entity.getPhotos(), entity.getMinGuests(), entity.getMaxGuests(), entity.getType(), entity.getAvailability(), entity.getPriceType(), entity.getPriceChanges(), entity.isAutomaticReservationAcceptance());
    }




}
