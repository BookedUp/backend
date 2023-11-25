package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.Date;
import java.util.List;

public class Accommodation {
    private Long id;
    private String name;
    private String description;
    private Address location;
    private double price;
    private int minGuests;
    private int maxGuests;
    private int cancellationDeadline;
    private boolean automaticReservationAcceptance;
    private AccommodationStatus status;
    private PriceType priceType;
    private AccommodationType type;
    private List<Amenity> amenities;
    private List<Photo> photos;
    private List<DateRange> availability;
    private List<PriceChange> priceChanges;
    private List<Reservation> reservations;
    private List<Review> reviews;
    private double averageRating;


    public Accommodation() {
    }

    public Accommodation(
            Long id, String name, Address address, String description,
            List<Amenity> amenities, List<Photo> photos,
            int minGuests, int maxGuests,
            AccommodationType accommodationType,
            List<DateRange> availability, double price,
            PriceType priceType, AccommodationStatus status,
            List<PriceChange> priceChanges, int cancellationDeadline,
            boolean automaticReservationAcceptance,
            List<Reservation> reservations, List<Review> reviews,
            double averageRating) {
        this.id = id;
        this.name = name;
        this.location = address;
        this.description = description;
        this.amenities = amenities;
        this.photos = photos;
        this.minGuests = minGuests;
        this.maxGuests = maxGuests;
        this.type = accommodationType;
        this.availability = availability;
        this.price = price;
        this.priceType = priceType;
        this.status = status;
        this.priceChanges = priceChanges;
        this.cancellationDeadline = cancellationDeadline;
        this.automaticReservationAcceptance = automaticReservationAcceptance;
        this.reservations = reservations;
        this.reviews = reviews;
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
        return location;
    }

    public void setAddress(Address address) {
        this.location = address;
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

    public int getCancellationDeadline() {
        return cancellationDeadline;
    }

    public void setCancellationDeadline(int cancellationDeadline) {
        this.cancellationDeadline = cancellationDeadline;
    }

    public boolean isAutomaticReservationAcceptance() {
        return automaticReservationAcceptance;
    }

    public void setAutomaticReservationAcceptance(boolean automaticReservationAcceptance) {
        this.automaticReservationAcceptance = automaticReservationAcceptance;
    }
    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public AccommodationType getType() { return type; }

    public void setType(AccommodationType type) {
        this.type = type;
    }

    public List<Reservation> getReservations() {return reservations;}

    public void setReservations(List<Reservation> reservations) {this.reservations = reservations;}

    public List<Review> getReviews() {return reviews;}

    public void setReviews(List<Review> reviews) {this.reviews = reviews;}

    // Function to copy values from another Accommodation
    public void copyValues(Accommodation accommodation) {
        this.id = accommodation.getId();
        this.name = accommodation.getName();
        this.location = accommodation.getAddress();
        this.description = accommodation.getDescription();
        this.amenities = accommodation.getAmenities();
        this.photos = accommodation.getPhotos();
        this.minGuests = accommodation.getMinGuests();
        this.maxGuests = accommodation.getMaxGuests();
        this.type = accommodation.getType();
        this.availability = accommodation.getAvailability();
        this.price = accommodation.getPrice();
        this.priceType = accommodation.getPriceType();
        this.status = accommodation.getStatus();
        this.priceChanges = accommodation.getPriceChanges();
        this.cancellationDeadline = accommodation.getCancellationDeadline();
        this.automaticReservationAcceptance = accommodation.isAutomaticReservationAcceptance();
        this.reservations = accommodation.getReservations();
        this.reviews = accommodation.getReviews();
        this.averageRating = accommodation.getAverageRating();
    }

    // Method to update availability
    public void updateAvailability(Date startDate, Date endDate) {
        // TODO: Implement method to update availability
    }

    // Method to update the base price
    public void updatePrice(double basePrice) {
        // TODO: Implement method to update the base price
    }

    // Method to update price details (e.g., discounts, promotions)
    public void updatePriceDetails(PriceChange priceDetails) {
        // TODO: Implement method to update price details
    }

    // Method to update additional details
    public void updateMoreDetails() {
        // TODO: Implement method to update more details
    }
}
