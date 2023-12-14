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

    @Query("SELECT a FROM Accommodation a WHERE a.host.id = :hostId AND a.status = 'ACTIVE' "  )
    List<Accommodation> findAllActiveByHostId(@Param("hostId") Long hostId);

    @Query("SELECT a FROM Accommodation a WHERE a.host.id = :hostId AND a.status = 'REJECTED' "  )
    List<Accommodation> findAllRejectedByHostId(@Param("hostId") Long hostId);

    @Query("SELECT a FROM Accommodation a WHERE a.host.id = :hostId AND (a.status = 'CREATED' OR  a.status = 'CHANGED') ")
    List<Accommodation> findAllRequestsByHostId(@Param("hostId") Long hostId);



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
            "WHERE a.status = 'ACTIVE' " +
            "AND (:accommodationType IS NULL OR a.type = :accommodationType)")
    List<Accommodation> filterAccommodationsByType(
            @Param("accommodationType") AccommodationType accommodationType);

}
