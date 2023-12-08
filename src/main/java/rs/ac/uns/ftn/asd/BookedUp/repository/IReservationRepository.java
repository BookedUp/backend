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

    @Query("SELECT r FROM Reservation r WHERE r.accommodation.id = :accommodationId")
    List<Reservation> findAllByAccommodationId(@Param("accommodationId") Long accommodationId);
}
