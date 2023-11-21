package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.List;

public class Accommodation {
    private Long id;
    private String name;
    private Address address;
    private String description;
    private List<Amenity> amenities; // Enum - wifi, kitchen, air conditioning, etc.
    private List<String> photos;
    private int minGuests;
    private int maxGuests;
    private AccommodationType accommodationType; // Enum
    private List<DateRange> availability;
    private double price;
    private PriceType priceType; // Enum
    private AccommodationStatus status; // Enum
    private List<PriceChange> priceChanges;
    private String cancellationDeadline;
    private boolean automaticReservationAcceptance;
    private List<Long> reservationIds;
    private List<Long> reviewIds;
    private double averageRating;

    public Accommodation() {
    }

    public Accommodation(
            Long id, String name, Address address, String description,
            List<Amenity> amenities, List<String> photos,
            int minGuests, int maxGuests,
            AccommodationType accommodationType,
            List<DateRange> availability, double price,
            PriceType priceType, AccommodationStatus status,
            List<PriceChange> priceChanges, String cancellationDeadline,
            boolean automaticReservationAcceptance,
            List<Long> reservationIds, List<Long> reviewIds,
            double averageRating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.amenities = amenities;
        this.photos = photos;
        this.minGuests = minGuests;
        this.maxGuests = maxGuests;
        this.accommodationType = accommodationType;
        this.availability = availability;
        this.price = price;
        this.priceType = priceType;
        this.status = status;
        this.priceChanges = priceChanges;
        this.cancellationDeadline = cancellationDeadline;
        this.automaticReservationAcceptance = automaticReservationAcceptance;
        this.reservationIds = reservationIds;
        this.reviewIds = reviewIds;
        this.averageRating = averageRating;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
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

    public AccommodationType getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(AccommodationType accommodationType) {
        this.accommodationType = accommodationType;
    }

    public List<DateRange> getAvailability() {
        return availability;
    }

    public void setAvailability(List<DateRange> availability) {
        this.availability = availability;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public AccommodationStatus getStatus() {
        return status;
    }

    public void setStatus(AccommodationStatus status) {
        this.status = status;
    }

    public List<PriceChange> getPriceChanges() {
        return priceChanges;
    }

    public void setPriceChanges(List<PriceChange> priceChanges) {
        this.priceChanges = priceChanges;
    }

    public String getCancellationDeadline() {
        return cancellationDeadline;
    }

    public void setCancellationDeadline(String cancellationDeadline) {
        this.cancellationDeadline = cancellationDeadline;
    }

    public boolean isAutomaticReservationAcceptance() {
        return automaticReservationAcceptance;
    }

    public void setAutomaticReservationAcceptance(boolean automaticReservationAcceptance) {
        this.automaticReservationAcceptance = automaticReservationAcceptance;
    }

    public List<Long> getReservationIds() {
        return reservationIds;
    }

    public void setReservationIds(List<Long> reservationIds) {
        this.reservationIds = reservationIds;
    }

    public List<Long> getReviewIds() {
        return reviewIds;
    }

    public void setReviewIds(List<Long> reviewIds) {
        this.reviewIds = reviewIds;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    // Function to copy values from another Accommodation
    public void copyValues(Accommodation accommodation) {
        this.id = accommodation.getId();
        this.name = accommodation.getName();
        this.address = accommodation.getAddress();
        this.description = accommodation.getDescription();
        this.amenities = accommodation.getAmenities();
        this.photos = accommodation.getPhotos();
        this.minGuests = accommodation.getMinGuests();
        this.maxGuests = accommodation.getMaxGuests();
        this.accommodationType = accommodation.getAccommodationType();
        this.availability = accommodation.getAvailability();
        this.price = accommodation.getPrice();
        this.priceType = accommodation.getPriceType();
        this.status = accommodation.getStatus();
        this.priceChanges = accommodation.getPriceChanges();
        this.cancellationDeadline = accommodation.getCancellationDeadline();
        this.automaticReservationAcceptance = accommodation.isAutomaticReservationAcceptance();
        this.reservationIds = accommodation.getReservationIds();
        this.reviewIds = accommodation.getReviewIds();
        this.averageRating = accommodation.getAverageRating();
    }
}
