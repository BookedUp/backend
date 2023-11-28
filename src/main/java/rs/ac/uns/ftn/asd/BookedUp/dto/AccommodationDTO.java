package rs.ac.uns.ftn.asd.BookedUp.dto;

import rs.ac.uns.ftn.asd.BookedUp.domain.*;

import java.util.List;

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
    private List<PriceChange> priceChanges;

    private AccommodationStatus status;

    private boolean automaticReservationAcceptance;

    public AccommodationDTO() {
    }

    public AccommodationDTO(Long id, String name, String description, Address address, List<Amenity> amenities, List<Photo> photos, int minGuests, int maxGuests, AccommodationType type, List<DateRange> availability, List<PriceChange> priceChanges, AccommodationStatus status, boolean automaticReservationAcceptance) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.amenities = amenities;
        this.photos = photos;
        this.minGuests = minGuests;
        this.maxGuests = maxGuests;
        this.type = type;
        this.availability = availability;
        this.priceChanges = priceChanges;
        this.status = status;
        this.automaticReservationAcceptance = automaticReservationAcceptance;
    }

    public AccommodationDTO(String name, String description, Address address, List<Amenity> amenities, List<Photo> photos, int minGuests, int maxGuests, AccommodationType type, List<DateRange> availability, List<PriceChange> priceChanges, AccommodationStatus status, boolean automaticReservationAcceptance) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.amenities = amenities;
        this.photos = photos;
        this.minGuests = minGuests;
        this.maxGuests = maxGuests;
        this.type = type;
        this.availability = availability;
        this.priceChanges = priceChanges;
        this.status = status;
        this.automaticReservationAcceptance = automaticReservationAcceptance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public int getMinGuests() {
        return minGuests;
    }

    public void setMinGuests(int minGuests) {
        this.minGuests = minGuests;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public AccommodationType getType() {
        return type;
    }

    public void setType(AccommodationType type) {
        this.type = type;
    }

    public List<DateRange> getAvailability() {
        return availability;
    }

    public void setAvailability(List<DateRange> availability) {
        this.availability = availability;
    }

    public List<PriceChange> getPriceChanges() {
        return priceChanges;
    }

    public void setPriceChanges(List<PriceChange> priceChanges) {
        this.priceChanges = priceChanges;
    }

    public boolean isAutomaticReservationAcceptance() {
        return automaticReservationAcceptance;
    }

    public void setAutomaticReservationAcceptance(boolean automaticReservationAcceptance) {
        this.automaticReservationAcceptance = automaticReservationAcceptance;
    }

    public AccommodationStatus getStatus() {
        return status;
    }

    public void setStatus(AccommodationStatus status) {
        this.status = status;
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
    }
}
