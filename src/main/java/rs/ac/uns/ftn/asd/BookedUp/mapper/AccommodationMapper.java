package rs.ac.uns.ftn.asd.BookedUp.mapper;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.HostDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.PhotoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AccommodationMapper  implements MapperInterface<Accommodation, AccommodationDTO> {

    //HostMapper hostMapper = new HostMapper();

    PhotoMapper photoMapper = new PhotoMapper();
    @Override
    public Accommodation toEntity(AccommodationDTO dto) {
        if (dto == null){
            return null;
        }

        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Review> reviews = new ArrayList<Review>();
        //Host host = hostMapper.toEntity(dto.getHostDTO());
        List<Photo> photos = new ArrayList<Photo>();
        if (dto.getPhotos() != null){
            for (PhotoDTO photoDTO : dto.getPhotos())
                photos.add(photoMapper.toEntity(photoDTO));
        }


        return new Accommodation(dto.getId(),  dto.getName(), dto.getDescription(), dto.getAddress(), 0.0, dto.getMinGuests(),  dto.getMaxGuests(), 0, dto.isAutomaticReservationAcceptance(), dto.getStatus(), dto.getPriceType(),  dto.getType(), dto.getAmenities(), photos, dto.getAvailability(), dto.getPriceChanges(), reservations, reviews, 0.0);

    }

    @Override
    public AccommodationDTO toDto(Accommodation entity) {
        if (entity == null){
            return null;
        }
        //HostDTO hostDTO = hostMapper.toDto(entity.getHost());
        List<PhotoDTO> photoDTOS = new ArrayList<PhotoDTO>();
        if (entity.getPhotos() != null){
            for (Photo photo : entity.getPhotos())
                photoDTOS.add(photoMapper.toDto(photo));
        }
        return new AccommodationDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getAddress(), entity.getAmenities(), photoDTOS, entity.getMinGuests(), entity.getMaxGuests(), entity.getType(), entity.getAvailability(), entity.getPriceType(), entity.getPriceChanges(), entity.isAutomaticReservationAcceptance(), entity.getStatus());
    }




}
