package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationStatisticsRepository;
import rs.ac.uns.ftn.asd.BookedUp.service.interfaces.ServiceInterface;

import java.util.Collection;

@Service
public class AccommodationStatisticsService implements ServiceInterface<AccommodationStatistics> {
    @Autowired
    private IAccommodationStatisticsRepository repository;

    @Override
    public Collection<AccommodationStatistics> getAll() {
        return repository.findAll();
    }

    @Override
    public AccommodationStatistics getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public AccommodationStatistics create(AccommodationStatistics statistics) throws Exception {
        if (statistics.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        return repository.save(statistics);
    }

    @Override
    public AccommodationStatistics save(AccommodationStatistics statistics) throws Exception {
        return repository.save(statistics);
    }

    //PROMENITI
//    @Override
//    public AccommodationStatistics update(AccommodationStatistics statisticsDTO) throws Exception {
//        AccommodationStatistics statistics = statisticsMapper.toEntity(statisticsDTO);
//        AccommodationStatistics statisticsToUpdate = repository.findById(statistics.getId()).orElse(null);
//        if (statisticsToUpdate == null) {
//            throw new Exception("The requested entity was not found.");
//        }
//        statisticsToUpdate.setAccommodation(statistics.getAccommodation());
//        statisticsToUpdate.setYear(statistics.getYear());
//        statisticsToUpdate.setDetails(statistics.getDetails());
//        statisticsToUpdate.setProfit(statistics.getProfit());
//        statisticsToUpdate.setNumberOfReservations(statistics.getNumberOfReservations());
//
//        AccommodationStatistics updatedStatistics = repository.save(statisticsToUpdate);
//        return statisticsMapper.toDto(updatedStatistics);
//    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
