package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationReport;

import java.util.Collection;

public interface IAccommodationReportRepository {
    Collection<AccommodationReport> getAll();
    AccommodationReport create(AccommodationReport report);
    AccommodationReport getById(Long id);
    AccommodationReport update(AccommodationReport report);
    void delete(Long id);
}
