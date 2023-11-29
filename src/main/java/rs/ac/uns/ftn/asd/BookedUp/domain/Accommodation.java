package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.enums.AccommodationStatus;
import rs.ac.uns.ftn.asd.BookedUp.enums.AccommodationType;
import rs.ac.uns.ftn.asd.BookedUp.enums.Amenity;
import rs.ac.uns.ftn.asd.BookedUp.enums.PriceType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation {
    private Long id;
    private String name;
    private String description;
    private Address address;
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

    // Function to copy values from another Accommodation
    public void copyValues(Accommodation accommodation) {
        this.name = accommodation.getName();
        this.address = accommodation.getAddress();
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

}
