package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatisticsMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public StatisticsMapper(ModelMapper modelMapper) {
        StatisticsMapper.modelMapper = modelMapper;
    }
    public static Statistics toEntity(StatisticsDTO dto) {
        return modelMapper.map(dto, Statistics.class);
    }

    public static StatisticsDTO toDto(Statistics entity) {
        return modelMapper.map(entity, StatisticsDTO.class);
    }

//    StatisticsDetailMapper statisticsDetailMapper = new StatisticsDetailMapper();
//
//    @Override
//    public Statistics toEntity(StatisticsDTO dto) {
//        if (dto == null){
//            return null;
//        }
//
//        List<StatisticsDetail> statisticsDetails = new ArrayList<StatisticsDetail>();
//        if(dto.getDetails() != null) {
//            for(StatisticsDetailDTO statisticsDetailDTO : dto.getDetails())
//                statisticsDetails.add(statisticsDetailMapper.toEntity(statisticsDetailDTO));
//        }
//
//        Statistics statistics = new Statistics();
//        statistics.setId(dto.getId());
//        statistics.setFromDate(dto.getFromDate());
//        statistics.setToDate(dto.getToDate());
//        statistics.setDetails(statisticsDetails);
//        statistics.setProfit(dto.getProfit());
//        statistics.setNumberOfReservations(dto.getNumberOfReservations());
//
//        return statistics;
//    }
//
//    @Override
//    public StatisticsDTO toDto(Statistics entity) {
//        if (entity == null){
//            return null;
//        }
//
//        List<StatisticsDetailDTO> statisticsDetailsDTOS = new ArrayList<StatisticsDetailDTO>();
//        if(entity.getDetails() != null) {
//            for(StatisticsDetail statisticsDetails: entity.getDetails())
//                statisticsDetailsDTOS.add(statisticsDetailMapper.toDto(statisticsDetails));
//        }
//
//        StatisticsDTO statisticsDTO = new StatisticsDTO();
//        statisticsDTO.setId(entity.getId());
//        statisticsDTO.setDetails(statisticsDetailsDTOS);
//        statisticsDTO.setFromDate(entity.getFromDate());
//        statisticsDTO.setToDate(entity.getToDate());
//        statisticsDTO.setProfit(entity.getProfit());
//        statisticsDTO.setNumberOfReservations(entity.getNumberOfReservations());
//
//        return statisticsDTO;
//    }
}
