package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.Report;

import java.util.Collection;

public interface IReportRepository {

    Collection<Report> getAll();
    Report create(Report report);
    Report getById(Long id);
    Report update(Report report);
    void delete(Long id);

}
