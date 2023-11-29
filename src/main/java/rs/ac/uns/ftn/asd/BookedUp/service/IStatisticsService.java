package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDTO;

import java.util.Collection;

public interface IStatisticsService {
    Collection<StatisticsDTO> getAll();
    StatisticsDTO getById(Long id);
    StatisticsDTO create(StatisticsDTO report) throws Exception;
    StatisticsDTO update(StatisticsDTO report) throws Exception;
    void delete(Long id);
}
