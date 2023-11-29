package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;

import java.util.Collection;

public interface IAccommodationReportService {
    Collection<AccommodationStatistics> getAll();
    AccommodationStatistics getById(Long id);
    AccommodationStatistics create(AccommodationStatistics report) throws Exception;
    AccommodationStatistics update(AccommodationStatistics report) throws Exception;
    void delete(Long id);
}
