package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationStatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationStatisticsMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationStatisticsRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AccommodationStatisticsService implements IAccommodationStatisticsService {
    @Autowired
    private IAccommodationStatisticsRepository statisticsRepository;

    @Autowired
    private AccommodationStatisticsMapper statisticsMapper;

    @Override
    public Collection<AccommodationStatisticsDTO> getAll() {
        Collection<AccommodationStatistics> statistics = (statisticsRepository.getAll());
        Collection<AccommodationStatisticsDTO> statisticsDTOS = new ArrayList<>();

        for (AccommodationStatistics statistic : statistics) {
            AccommodationStatisticsDTO accommodationStatisticsDTO = statisticsMapper.toDto(statistic);
            statisticsDTOS.add(accommodationStatisticsDTO);
        }

        return statisticsDTOS;
    }

    @Override
    public AccommodationStatisticsDTO getById(Long id) {
        AccommodationStatistics statistics = statisticsRepository.getById(id);
        return statisticsMapper.toDto(statistics);
    }

    @Override
    public AccommodationStatisticsDTO create(AccommodationStatisticsDTO statisticsDTO) throws Exception {
        if (statisticsDTO.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        AccommodationStatistics statistics = statisticsMapper.toEntity(statisticsDTO);
        AccommodationStatistics savedReport = statisticsRepository.create(statistics);
        return statisticsMapper.toDto(savedReport);
    }

    //PROMENITI
    @Override
    public AccommodationStatisticsDTO update(AccommodationStatisticsDTO statisticsDTO) throws Exception {
        AccommodationStatistics statistics = statisticsMapper.toEntity(statisticsDTO);
        AccommodationStatistics statisticsToUpdate = statisticsRepository.getById(statistics.getId());
        if (statisticsToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        statisticsToUpdate.setAccommodation(statistics.getAccommodation());
        statisticsToUpdate.setYear(statistics.getYear());
        statisticsToUpdate.setDetails(statistics.getDetails());
        statisticsToUpdate.setProfit(statistics.getProfit());
        statisticsToUpdate.setNumberOfReservations(statistics.getNumberOfReservations());

        AccommodationStatistics updatedStatistics = statisticsRepository.update(statisticsToUpdate);
        return statisticsMapper.toDto(updatedStatistics);
    }

    @Override
    public void delete(Long id) {
        statisticsRepository.delete(id);
    }
}
