package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;

import java.util.Collection;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {

}
