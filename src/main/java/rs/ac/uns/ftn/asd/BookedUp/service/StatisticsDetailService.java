package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.StatisticsDetailMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IStatisticsDetailRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class StatisticsDetailService implements IStatisticsDetailService {
    @Autowired
    private IStatisticsDetailRepository statisticsDetailRepository;

    @Autowired
    private StatisticsDetailMapper statisticsDetailMapper;

    @Override
    public Collection<StatisticsDetailDTO> getAll() {
        Collection<StatisticsDetail> statisticsDetails = (statisticsDetailRepository.getAll());
        Collection<StatisticsDetailDTO> statisticsDetailDTOS = new ArrayList<>();

        for (StatisticsDetail statisticsDetail : statisticsDetails) {
            StatisticsDetailDTO statisticsDetailDTO = statisticsDetailMapper.toDto(statisticsDetail);
            statisticsDetailDTOS.add(statisticsDetailDTO);
        }

        return statisticsDetailDTOS;

    }

    @Override
    public StatisticsDetailDTO getById(Long id) {
        StatisticsDetail statisticsDetail = statisticsDetailRepository.getById(id);
        return statisticsDetailMapper.toDto(statisticsDetail);
    }

    @Override
    public StatisticsDetailDTO create(StatisticsDetailDTO statisticsDetailDto) throws Exception {
        if (statisticsDetailDto.getId() != null) {
            throw new Exception("Id must be null when persisting a new entity.");
        }
        StatisticsDetail statisticsDetail = statisticsDetailMapper.toEntity(statisticsDetailDto);
        StatisticsDetail savedReportDetail = statisticsDetailRepository.create(statisticsDetail);
        return statisticsDetailMapper.toDto(savedReportDetail);
    }

    @Override
    public StatisticsDetailDTO update(StatisticsDetailDTO statisticsDetailDto) throws Exception {
        StatisticsDetail statisticsDetail = statisticsDetailMapper.toEntity(statisticsDetailDto);
        StatisticsDetail statisticsDetailToUpdate = statisticsDetailRepository.getById(statisticsDetail.getId());
        if (statisticsDetailToUpdate == null) {
            throw new Exception("The requested entity was not found.");
        }
        statisticsDetailToUpdate.setProfit(statisticsDetail.getProfit());
        statisticsDetailToUpdate.setNumberOfReservations(statisticsDetail.getNumberOfReservations());

        StatisticsDetail updatedStatisticsDetail = statisticsDetailRepository.update(statisticsDetailToUpdate);
        return statisticsDetailMapper.toDto(updatedStatisticsDetail);
    }

    @Override
    public void delete(Long id) {
        statisticsDetailRepository.delete(id);
    }
}
