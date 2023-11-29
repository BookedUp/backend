package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatisticsMapper implements MapperInterface<Statistics, StatisticsDTO> {

    StatisticsDetailMapper statisticsDetailMapper = new StatisticsDetailMapper();

    @Override
    public Statistics toEntity(StatisticsDTO dto) {
        if (dto == null){
            return null;
        }

        List<StatisticsDetail> statisticsDetails = new ArrayList<StatisticsDetail>();
        if(dto.getDetails() != null) {
            for(StatisticsDetailDTO statisticsDetailDTO : dto.getDetails())
                statisticsDetails.add(statisticsDetailMapper.toEntity(statisticsDetailDTO));
        }

        Statistics statistics = new Statistics();
        statistics.setId(dto.getId());
        statistics.setFromDate(dto.getFromDate());
        statistics.setToDate(dto.getToDate());
        statistics.setDetails(statisticsDetails);
        statistics.setProfit(dto.getProfit());
        statistics.setNumberOfReservations(dto.getNumberOfReservations());

        return statistics;
    }

    @Override
    public StatisticsDTO toDto(Statistics entity) {
        if (entity == null){
            return null;
        }

        List<StatisticsDetailDTO> statisticsDetailsDTOS = new ArrayList<StatisticsDetailDTO>();
        if(entity.getDetails() != null) {
            for(StatisticsDetail statisticsDetails: entity.getDetails())
                statisticsDetailsDTOS.add(statisticsDetailMapper.toDto(statisticsDetails));
        }

        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setId(entity.getId());
        statisticsDTO.setDetails(statisticsDetailsDTOS);
        statisticsDTO.setFromDate(entity.getFromDate());
        statisticsDTO.setToDate(entity.getToDate());
        statisticsDTO.setProfit(entity.getProfit());
        statisticsDTO.setNumberOfReservations(entity.getNumberOfReservations());

        return statisticsDTO;
    }
}
