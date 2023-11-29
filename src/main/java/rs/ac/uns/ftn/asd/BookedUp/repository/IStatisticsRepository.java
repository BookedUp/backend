package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;

import java.util.Collection;

public interface IStatisticsRepository {

    Collection<Statistics> getAll();
    Statistics create(Statistics report);
    Statistics getById(Long id);
    Statistics update(Statistics report);
    void delete(Long id);

}
