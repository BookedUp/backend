package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.AccommodationType;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.Amenity;

import java.util.Date;
import java.util.List;

public interface IAccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("SELECT a FROM Accommodation a WHERE a.host.id = :hostId")
    List<Accommodation> findAllByHostId(@Param("hostId") Long hostId);


    @Query("SELECT a FROM Accommodation a WHERE  a.status = 'CREATED'")
    List<Accommodation> findAllCreated();

    @Query("SELECT a FROM Accommodation a WHERE  a.status = 'CHANGED'")
    List<Accommodation> findAllChanged();

    @Query("SELECT a FROM Accommodation a WHERE  a.status = 'CHANGED' OR a.status='CREATED'")
    List<Accommodation> findAllModified();

    //ako ne radi nesto PRVO SUMNJIV OVAJ FETCH NJEGA SAM MENJALA
    @Query("SELECT DISTINCT a FROM Accommodation a " +
            "JOIN FETCH a.availability dr " +
            "WHERE a.status = 'ACTIVE' " +
            "AND (" +
            "   COALESCE(:location, '') = '' OR " +
            "   a.address.country = :location OR " +
            "   a.address.city = :location OR " +
            "   a.address.streetAndNumber = :location" +
            ") " +
            "AND a.minGuests <= :guestsNumber AND a.maxGuests >= :guestsNumber " +
            "AND (:startDate BETWEEN dr.startDate AND dr.endDate OR :endDate BETWEEN dr.startDate AND dr.endDate)")
    List<Accommodation> searchAccommodations(
            @Param("location") String location,
            @Param("guestsNumber") int guests,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    @Query("SELECT a FROM Accommodation a " +
            "LEFT JOIN a.amenities amenity " +
            "WHERE (?1 IS NULL OR amenity IN ?1) " +
            "AND (?2 IS NULL OR a.type = ?2) " +
            "AND (?3 IS NULL OR a.price >= ?3) " +
            "AND (?4 IS NULL OR a.price <= ?4)")
    List<Accommodation> filterAccommodations(
            List<Amenity> amenities,
            AccommodationType accommodationType,
            Double minPrice,
            Double maxPrice);


    @Query("SELECT a, COALESCE(COUNT(r.id), 0) AS reservationCount FROM Accommodation a " +
            "LEFT JOIN a.reservations r " +
            "ON r.status = 'COMPLETED' " +
            "WHERE a.status = 'ACTIVE' " +
            "GROUP BY a.id " +
            "ORDER BY COALESCE(COUNT(r.id), 0) DESC")
    List<Object[]> findMostPopular();





}
