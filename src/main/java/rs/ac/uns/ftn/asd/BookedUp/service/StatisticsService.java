package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.StatisticsMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IStatisticsRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class StatisticsService implements IStatisticsService {
    @Autowired
    private IStatisticsRepository statisticsRepository;

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Collection<StatisticsDTO> getAll() {
        Collection<Statistics> statistics = (statisticsRepository.getAll());
        Collection<StatisticsDTO> statisticsDTOS = new ArrayList<>();

        for (Statistics statistic : statistics) {
            StatisticsDTO statisticsDTO = statisticsMapper.toDto(statistic);
            statisticsDTOS.add(statisticsDTO);
        }

        return statisticsDTOS;
    }

    @Override
    public StatisticsDTO getById(Long id) {
        Statistics statistics = statisticsRepository.getById(id);
        return statisticsMapper.toDto(statistics);
    }

    @Override
    public StatisticsDTO create(StatisticsDTO statisticsDTO) throws Exception {
        if (statisticsDTO.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Statistics statistics = statisticsMapper.toEntity(statisticsDTO);
        Statistics createdStatistics =  statisticsRepository.create(statistics);
        return statisticsMapper.toDto(createdStatistics);
    }

    @Override
    public StatisticsDTO update(StatisticsDTO statisticsDTO) throws Exception {
        Statistics statistics = statisticsMapper.toEntity(statisticsDTO);
        Statistics statisticsToUpdate = statisticsRepository.getById(statistics.getId());
        if (statisticsToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        statisticsToUpdate.setFromDate(statistics.getFromDate());
        statisticsToUpdate.setToDate(statistics.getToDate());
        statisticsToUpdate.setDetails(statistics.getDetails());
        statisticsToUpdate.setProfit(statistics.getProfit());
        statisticsToUpdate.setNumberOfReservations(statistics.getNumberOfReservations());

        Statistics updatedStatistics = statisticsRepository.update(statisticsToUpdate);
        return statisticsMapper.toDto(updatedStatistics);
    }

    @Override
    public void delete(Long id) {
        statisticsRepository.delete(id);
    }
}
