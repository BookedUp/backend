package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Report;

import java.util.Collection;

public interface IReportService {
    Collection<Report> getAll();
    Report getById(Long id);
    Report create(Report report) throws Exception;
    Report update(Report report) throws Exception;
    void delete(Long id);
}
