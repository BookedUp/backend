package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;

import java.util.Collection;

public interface IAccommodationStatisticsRepository {
    Collection<AccommodationStatistics> getAll();
    AccommodationStatistics create(AccommodationStatistics report);
    AccommodationStatistics getById(Long id);
    AccommodationStatistics update(AccommodationStatistics report);
    void delete(Long id);
}
