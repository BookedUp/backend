package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;

@Component
public class StatisticsDetailMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public StatisticsDetailMapper(ModelMapper modelMapper) {
        StatisticsDetailMapper.modelMapper = modelMapper;
    }
    public static StatisticsDetail toEntity(StatisticsDetailDTO dto) {
        return modelMapper.map(dto, StatisticsDetail.class);
    }

    public static StatisticsDetailDTO toDto(StatisticsDetail entity) {
        return modelMapper.map(entity, StatisticsDetailDTO.class);
    }


//    @Override
//    public StatisticsDetail toEntity(StatisticsDetailDTO dto) {
//        if (dto == null){
//            return null;
//        }
//
//        StatisticsDetail statisticsDetail = new StatisticsDetail();
//        statisticsDetail.setId(dto.getId());
//        statisticsDetail.setKey(dto.getKey());
//        statisticsDetail.setProfit(dto.getProfit());
//        statisticsDetail.setNumberOfReservations(dto.getNumberOfReservations());
//
//        return statisticsDetail;
//    }
//
//    @Override
//    public StatisticsDetailDTO toDto(StatisticsDetail entity) {
//        if (entity == null){
//            return null;
//        }
//
//        StatisticsDetailDTO statisticsDetailDTO = new StatisticsDetailDTO();
//        statisticsDetailDTO.setId(entity.getId());
//        statisticsDetailDTO.setKey(entity.getKey());
//        statisticsDetailDTO.setProfit(entity.getProfit());
//        statisticsDetailDTO.setNumberOfReservations(entity.getNumberOfReservations());
//
//        return statisticsDetailDTO;
//    }
}
