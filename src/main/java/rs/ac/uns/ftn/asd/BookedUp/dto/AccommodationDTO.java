package rs.ac.uns.ftn.asd.BookedUp.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.enums.AccommodationStatus;
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
    private AddressDTO address;
    private List<Amenity> amenities;
    private List<PhotoDTO> photos;
    private int minGuests;
    private int maxGuests;
    private AccommodationType type;
    private List<DateRangeDTO> availability;
    private PriceType priceType;
    private List<PriceChangeDTO> priceChanges;
    private boolean automaticReservationAcceptance;
    private AccommodationStatus status;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private HostDTO host;
    private double price;


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
        this.status = dto.getStatus();
        this.host = dto.getHost();
    }
}
