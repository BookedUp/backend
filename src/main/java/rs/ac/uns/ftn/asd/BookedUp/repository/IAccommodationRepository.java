package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.enums.AccommodationType;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.enums.Amenity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IAccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("SELECT a FROM Accommodation a WHERE a.host.id = :hostId")
    List<Accommodation> findAllByHostId(@Param("hostId") Long hostId);


    @Query("SELECT a FROM Accommodation a WHERE  a.status = 'CREATED'")
    List<Accommodation> findAllCreatedAccommodations();

    @Query("SELECT a FROM Accommodation a WHERE  a.status = 'CHANGED'")
    List<Accommodation> findAllChangedAccommodations();

    //ako ne radi nesto PRVO SUMNJIV OVAJ FETCH NJEGA SAM MENJALA
    @Query("SELECT DISTINCT a FROM Accommodation a " +
            "JOIN FETCH a.availability dr " +
            "WHERE a.status = 'ACTIVE' " +
            "AND (a.address.country = :country OR :country IS NULL) " +
            "AND (a.address.city = :city OR :city IS NULL) " +
            "AND a.minGuests <= :guestsNumber AND a.maxGuests >= :guestsNumber " +
            "AND (:startDate BETWEEN dr.startDate AND dr.endDate OR :endDate BETWEEN dr.startDate AND dr.endDate)")
    List<Accommodation> searchAccommodations(
            @Param("country") String country,
            @Param("city") String city,
            @Param("guestsNumber") int guests,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);


}
