package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;

import java.util.Collection;

public interface IReportDetailService {
    Collection<StatisticsDetail> getAll();
    StatisticsDetail getById(Long id);
    StatisticsDetail create(StatisticsDetail reportDetail) throws Exception;
    StatisticsDetail update(StatisticsDetail reportDetail) throws Exception;
    void delete(Long id);
}
