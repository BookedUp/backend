package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationStatisticsDTO;

import java.util.Collection;

public interface IAccommodationStatisticsService {
    Collection<AccommodationStatisticsDTO> getAll();
    AccommodationStatisticsDTO getById(Long id);
    AccommodationStatisticsDTO create(AccommodationStatisticsDTO statisticsDTO) throws Exception;
    AccommodationStatisticsDTO update(AccommodationStatisticsDTO statisticsDTO) throws Exception;
    void delete(Long id);
}
