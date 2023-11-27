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
        List<PriceChange> priceChangeList = new ArrayList<PriceChange>();
        priceChangeList.add(new PriceChange(new Date(), dto.getPrice()));

        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Review> reviews = new ArrayList<Review>();


        return new Accommodation(dto.getName(),dto.getAddress(), dto.getDescription(), dto.getAmenities(), dto.getPhotos(), dto.getMinGuests(), dto.getMaxGuests(), dto.getType(), dto.getAvailability(), dto.getPrice(), PriceType.PER_NIGHT, AccommodationStatus.INACTIVE, priceChangeList, 3, true, reservations,reviews, 0.0 );

    }

    @Override
    public AccommodationDTO toDto(Accommodation entity) {
        return new AccommodationDTO(entity.getName(), entity.getDescription(), entity.getAddress(), entity.getAmenities(), entity.getPhotos(), entity.getMinGuests(), entity.getMaxGuests(), entity.getType(), entity.getAvailability(), entity.getPrice());
    }
}
