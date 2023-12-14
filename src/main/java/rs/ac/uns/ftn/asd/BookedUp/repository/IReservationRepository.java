package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;

import java.util.Collection;
import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.guest.id = :guestId")
    List<Reservation> findAllByGuestId(@Param("guestId") Long guestId);

    @Query("SELECT r FROM Reservation r JOIN r.accommodation a JOIN a.host h WHERE h.id = :hostId")
    List<Reservation> findAllByHostId(@Param("hostId") Long hostId);

    @Query("SELECT r FROM Reservation r JOIN r.accommodation a JOIN a.host h WHERE h.id = :hostId AND r.status = 'ACCEPTED'")
    List<Reservation> findAllAcceptedByHostId(@Param("hostId") Long hostId);

    @Query("SELECT r FROM Reservation r JOIN r.accommodation a JOIN a.host h WHERE h.id = :hostId AND r.status = 'REJECTED'")
    List<Reservation> findAllRejectedByHostId(@Param("hostId") Long hostId);

    @Query("SELECT r FROM Reservation r JOIN r.accommodation a JOIN a.host h WHERE h.id = :hostId AND r.status = 'CREATED'")
    List<Reservation> findAllCreatedByHostId(@Param("hostId") Long hostId);

    @Query("SELECT r FROM Reservation r JOIN r.accommodation a JOIN a.host h WHERE h.id = :hostId AND r.status = 'CANCELLED'")
    List<Reservation> findAllCancelledByHostId(@Param("hostId") Long hostId);

    @Query("SELECT r FROM Reservation r JOIN r.accommodation a JOIN a.host h WHERE h.id = :hostId AND r.status = 'COMPLETED'")
    List<Reservation> findAllCompletedByHostId(@Param("hostId") Long hostId);
    @Query("SELECT r FROM Reservation r WHERE r.accommodation.id = :accommodationId")
    List<Reservation> findAllByAccommodationId(@Param("accommodationId") Long accommodationId);
}
