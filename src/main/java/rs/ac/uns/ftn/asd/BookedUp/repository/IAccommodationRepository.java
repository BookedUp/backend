package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;

import java.util.Collection;
import java.util.List;

public interface IAccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("SELECT a FROM Accommodation a WHERE a.host.id = :hostId")
    List<Accommodation> findAllByHostId(@Param("hostId") Long hostId);
}
