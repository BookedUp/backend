package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.enums.AccommodationType;
import rs.ac.uns.ftn.asd.BookedUp.enums.Amenity;
import rs.ac.uns.ftn.asd.BookedUp.enums.PriceType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDTO {

    private Long id;
    private String name;
    private String description;
    private Address address;
    private List<Amenity> amenities;
    private List<Photo> photos;
    private int minGuests;
    private int maxGuests;
    private AccommodationType type;
    private List<DateRange> availability;
    private PriceType priceType;
    private List<PriceChange> priceChanges;
    private boolean automaticReservationAcceptance;


    public AccommodationDTO(String name, String description, Address address, List<Amenity> amenities, List<Photo> photos, int minGuests, int maxGuests, AccommodationType type, List<DateRange> availability, PriceType priceType, List<PriceChange> priceChanges, boolean automaticReservationAcceptance) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.amenities = amenities;
        this.photos = photos;
        this.minGuests = minGuests;
        this.maxGuests = maxGuests;
        this.type = type;
        this.availability = availability;
        this.priceType = priceType;
        this.priceChanges = priceChanges;
        this.automaticReservationAcceptance = automaticReservationAcceptance;
    }

    public void copyValues(AccommodationDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.address = dto.getAddress();
        this.amenities = dto.getAmenities();
        this.photos = dto.getPhotos();
        this.minGuests = dto.getMinGuests();
        this.maxGuests = dto.getMaxGuests();
        this.type = dto.getType();
        this.availability = dto.getAvailability();
        this.priceChanges = dto.getPriceChanges();
        this.automaticReservationAcceptance = dto.isAutomaticReservationAcceptance();
        this.priceType = dto.getPriceType();
    }
}
