package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Review;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;

import java.util.Collection;
import java.util.List;

public interface IReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.accommodation.id = :accommodationId")
    List<Review> findAllByAccommodationId(@Param("accommodationId") Long accommodationId);

    @Query("SELECT r FROM Review r WHERE r.guest.id = :guestId AND r.accommodation IS NOT NULL AND r.isReviewActive = true")
    List<Review> findAllAccommodationReviewsByGuestId(@Param("guestId") Long guestId);

    @Query("SELECT r FROM Review r WHERE r.guest.id = :guestId AND r.host IS NOT NULL AND r.isReviewActive = true")
    List<Review> findAllHostReviewsByGuestId(@Param("guestId") Long guestId);

    @Query("SELECT r FROM Review r WHERE r.guest.id = :guestId AND r.isReviewActive = true")
    List<Review> findAllByGuestId(@Param("guestId") Long guestId);


}
