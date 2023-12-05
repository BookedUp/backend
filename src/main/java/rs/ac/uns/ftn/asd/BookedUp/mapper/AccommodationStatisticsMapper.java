package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationStatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccommodationStatisticsMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public AccommodationStatisticsMapper(ModelMapper modelMapper) {
        AccommodationStatisticsMapper.modelMapper = modelMapper;
    }
    public static AccommodationStatistics toEntity(AccommodationStatisticsDTO dto) {
        return modelMapper.map(dto, AccommodationStatistics.class);
    }

    public static AccommodationStatisticsDTO toDto(AccommodationStatistics entity) {
        return modelMapper.map(entity, AccommodationStatisticsDTO.class);
    }

//    AccommodationMapper accommodationMapper = new AccommodationMapper();
//    StatisticsDetailMapper statisticsDetailMapper = new StatisticsDetailMapper();
//
//    @Override
//    public AccommodationStatistics toEntity(AccommodationStatisticsDTO dto) {
//        if (dto == null){
//            return null;
//        }
//
//        Accommodation accommodation = accommodationMapper.toEntity(dto.getAccommodationDto());
//        List<StatisticsDetail> statisticsDetails = new ArrayList<StatisticsDetail>();
//        if(dto.getDetailsDto() != null) {
//            for(StatisticsDetailDTO statisticsDetailDTO : dto.getDetailsDto())
//                statisticsDetails.add(statisticsDetailMapper.toEntity(statisticsDetailDTO));
//        }
//
//        AccommodationStatistics accommodationStatistics = new AccommodationStatistics();
//        accommodationStatistics.setId(dto.getId());
//        accommodationStatistics.setProfit(dto.getProfit());
//        accommodationStatistics.setYear(dto.getYear());
//        accommodationStatistics.setNumberOfReservations(dto.getNumberOfReservations());
//        accommodationStatistics.setDetails(statisticsDetails);
//        accommodationStatistics.setAccommodation(accommodation);
//
//        return accommodationStatistics;
//    }
//
//    @Override
//    public AccommodationStatisticsDTO toDto(AccommodationStatistics entity) {
//        if (entity == null){
//            return null;
//        }
//
//        AccommodationDTO accommodationDTO = accommodationMapper.toDto(entity.getAccommodation());
//        List<StatisticsDetailDTO> statisticsDetailsDTOS = new ArrayList<StatisticsDetailDTO>();
//        if(entity.getDetails() != null) {
//            for(StatisticsDetail statisticsDetails: entity.getDetails())
//                statisticsDetailsDTOS.add(statisticsDetailMapper.toDto(statisticsDetails));
//        }
//
//        AccommodationStatisticsDTO accommodationStatisticsDTO = new AccommodationStatisticsDTO();
//        accommodationStatisticsDTO.setId(entity.getId());
//        accommodationStatisticsDTO.setProfit(entity.getProfit());
//        accommodationStatisticsDTO.setYear(entity.getYear());
//        accommodationStatisticsDTO.setNumberOfReservations(entity.getNumberOfReservations());
//        accommodationStatisticsDTO.setAccommodationDto(accommodationDTO);
//        accommodationStatisticsDTO.setDetailsDto(statisticsDetailsDTOS);
//
//        return accommodationStatisticsDTO;
//    }
}
