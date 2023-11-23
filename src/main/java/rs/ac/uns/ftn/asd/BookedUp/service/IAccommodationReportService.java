package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationReport;

import java.util.Collection;

public interface IAccommodationReportService {
    Collection<AccommodationReport> getAll();
    AccommodationReport getById(Long id);
    AccommodationReport create(AccommodationReport report) throws Exception;
    AccommodationReport update(AccommodationReport report) throws Exception;
    void delete(Long id);
}
