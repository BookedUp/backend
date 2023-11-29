package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;

@Component
public class StatisticsDetailMapper implements MapperInterface<StatisticsDetail, StatisticsDetailDTO> {


    @Override
    public StatisticsDetail toEntity(StatisticsDetailDTO dto) {
        if (dto == null){
            return null;
        }

        StatisticsDetail statisticsDetail = new StatisticsDetail();
        statisticsDetail.setId(dto.getId());
        statisticsDetail.setKey(dto.getKey());
        statisticsDetail.setProfit(dto.getProfit());
        statisticsDetail.setNumberOfReservations(dto.getNumberOfReservations());

        return statisticsDetail;
    }

    @Override
    public StatisticsDetailDTO toDto(StatisticsDetail entity) {
        if (entity == null){
            return null;
        }

        StatisticsDetailDTO statisticsDetailDTO = new StatisticsDetailDTO();
        statisticsDetailDTO.setId(entity.getId());
        statisticsDetailDTO.setKey(entity.getKey());
        statisticsDetailDTO.setProfit(entity.getProfit());
        statisticsDetailDTO.setNumberOfReservations(entity.getNumberOfReservations());

        return statisticsDetailDTO;
    }
}
