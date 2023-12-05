package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;

import java.util.Collection;

public interface IAccommodationStatisticsRepository extends JpaRepository<AccommodationStatistics, Long> {
    
}
