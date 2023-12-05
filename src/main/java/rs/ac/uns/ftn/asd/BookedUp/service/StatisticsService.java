package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.StatisticsMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IStatisticsRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class StatisticsService implements ServiceInterface<Statistics> {
    @Autowired
    private IStatisticsRepository repository;


    @Override
    public Collection<Statistics> getAll() {
        return repository.findAll();
    }

    @Override
    public Statistics getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Statistics create(Statistics statistics) throws Exception {
        if (statistics.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        return repository.save(statistics);
    }

    @Override
    public Statistics save(Statistics statistics) throws Exception {
        return repository.save(statistics);
    }

//    @Override
//    public StatisticsDTO update(StatisticsDTO statisticsDTO) throws Exception {
//        Statistics statistics = statisticsMapper.toEntity(statisticsDTO);
//        Statistics statisticsToUpdate = repository.findById(statistics.getId()).orElse(null);
//        if (statisticsToUpdate == null) {
//            throw new Exception("The requested entity was not found.");
//        }
//        statisticsToUpdate.setFromDate(statistics.getFromDate());
//        statisticsToUpdate.setToDate(statistics.getToDate());
//        statisticsToUpdate.setDetails(statistics.getDetails());
//        statisticsToUpdate.setProfit(statistics.getProfit());
//        statisticsToUpdate.setNumberOfReservations(statistics.getNumberOfReservations());
//
//        Statistics updatedStatistics = repository.save(statisticsToUpdate);
//        return statisticsMapper.toDto(updatedStatistics);
//    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
