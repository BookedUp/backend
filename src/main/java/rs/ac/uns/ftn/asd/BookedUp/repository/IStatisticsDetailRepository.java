package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Review;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;

import java.util.Collection;

public interface IStatisticsDetailRepository extends JpaRepository<StatisticsDetail, Long> {

}
