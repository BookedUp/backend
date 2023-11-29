package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;

import java.util.Collection;

public interface IReportService {
    Collection<Statistics> getAll();
    Statistics getById(Long id);
    Statistics create(Statistics report) throws Exception;
    Statistics update(Statistics report) throws Exception;
    void delete(Long id);
}
