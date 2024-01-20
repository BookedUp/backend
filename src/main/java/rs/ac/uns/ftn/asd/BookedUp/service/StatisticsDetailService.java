package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.repository.IStatisticsDetailRepository;
import rs.ac.uns.ftn.asd.BookedUp.service.interfaces.ServiceInterface;

import java.util.Collection;

@Service
public class StatisticsDetailService implements ServiceInterface<StatisticsDetail> {
    @Autowired
    private IStatisticsDetailRepository repository;

    @Override
    public Collection<StatisticsDetail> getAll() {
        return repository.findAll();
    }

    @Override
    public StatisticsDetail getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public StatisticsDetail create(StatisticsDetail statisticsDetail) throws Exception {
        if (statisticsDetail.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        return repository.save(statisticsDetail);
    }

    @Override
    public StatisticsDetail save(StatisticsDetail statisticsDetail) throws Exception {
        return repository.save(statisticsDetail);
    }

//    @Override
//    public StatisticsDetailDTO update(StatisticsDetailDTO statisticsDetailDto) throws Exception {
//        StatisticsDetail statisticsDetail = statisticsDetailMapper.toEntity(statisticsDetailDto);
//        StatisticsDetail statisticsDetailToUpdate = repository.findById(statisticsDetail.getId()).orElse(null);
//        if (statisticsDetailToUpdate == null) {
//            throw new Exception("The requested entity was not found.");
//        }
//        statisticsDetailToUpdate.setProfit(statisticsDetail.getProfit());
//        statisticsDetailToUpdate.setNumberOfReservations(statisticsDetail.getNumberOfReservations());
//
//        StatisticsDetail updatedStatisticsDetail = repository.save(statisticsDetailToUpdate);
//        return statisticsDetailMapper.toDto(updatedStatisticsDetail);
//    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
