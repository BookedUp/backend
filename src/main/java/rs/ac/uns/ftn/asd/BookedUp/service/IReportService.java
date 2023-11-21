package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Report;

import java.util.Collection;

public interface IReportService {
    Collection<Report> getAll();
    Report getById(Long id);
    Report create(Report userReport) throws Exception;
    Report update(Report userReport) throws Exception;
    void delete(Long id);
}
