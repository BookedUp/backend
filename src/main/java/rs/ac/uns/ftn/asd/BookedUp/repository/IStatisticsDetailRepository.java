package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;

import java.util.Collection;

public interface IStatisticsDetailRepository {

    Collection<StatisticsDetail> getAll();
    StatisticsDetail create(StatisticsDetail reportDetail);
    StatisticsDetail getById(Long id);
    StatisticsDetail update(StatisticsDetail reportDetail);
    void delete(Long id);

}
