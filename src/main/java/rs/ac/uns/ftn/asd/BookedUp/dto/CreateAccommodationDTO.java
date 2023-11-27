package rs.ac.uns.ftn.asd.BookedUp.dto;

import rs.ac.uns.ftn.asd.BookedUp.domain.*;

import java.util.List;

public class CreateAccommodationDTO {
    private String name;
    private String description;
    private Address address;
    private List<Amenity> amenities;
    private List<Photo> photos;
    private int minGuests;
    private int maxGuests;
    private AccommodationType type;
    private List<DateRange> availability;
    private double price;

    public CreateAccommodationDTO() {
    }

    public CreateAccommodationDTO(String name, String description, Address address, List<Amenity> amenities, List<Photo> photos, int minGuests, int maxGuests, AccommodationType type, List<DateRange> availability, double price) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.amenities = amenities;
        this.photos = photos;
        this.minGuests = minGuests;
        this.maxGuests = maxGuests;
        this.type = type;
        this.availability = availability;
        this.price = price;
    }


    public CreateAccommodationDTO(Accommodation accommodation) {
        this(
                accommodation.getName(),
                accommodation.getDescription(),
                accommodation.getAddress(),
                accommodation.getAmenities(),
                accommodation.getPhotos(),
                accommodation.getMinGuests(),
                accommodation.getMaxGuests(),
                accommodation.getType(),
                accommodation.getAvailability(),
                accommodation.getPrice()
        );
    }
}
