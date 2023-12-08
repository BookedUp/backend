package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationStatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationStatisticsMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.StatisticsDetailMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationStatisticsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accommodation-report")
public class AccommodationStatisticsController {
    @Autowired
    private AccommodationStatisticsService service;

    /*url: /api/accommodation-statistics GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AccommodationStatisticsDTO>> getStatistics() {
        Collection<AccommodationStatistics> statistics = service.getAll();
        Collection<AccommodationStatisticsDTO> statisticsDTO = statistics.stream()
                .map(AccommodationStatisticsMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(statisticsDTO, HttpStatus.OK);
    }

    /* url: /api/accommodation-statistics/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationStatisticsDTO> getStatistic(@PathVariable("id") Long id) {
        AccommodationStatistics statistics = service.getById(id);

        if (statistics == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(AccommodationStatisticsMapper.toDto(statistics), HttpStatus.OK);
    }

    /*url: /api/accommodation-statistics POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationStatisticsDTO> createStatistic(@RequestBody AccommodationStatisticsDTO statisticsDTO) throws Exception {
        AccommodationStatistics createdStatistics = null;

        try {
            createdStatistics = service.create(AccommodationStatisticsMapper.toEntity(statisticsDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AccommodationStatisticsDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(AccommodationStatisticsMapper.toDto(createdStatistics), HttpStatus.CREATED);
    }

    /* url: /api/accommodation-statistics/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationStatisticsDTO> updateStatistic(@Valid @RequestBody AccommodationStatisticsDTO statisticsDTO, @PathVariable Long id)
            throws Exception {
        AccommodationStatistics statisticForUpdate = service.getById(id);

        if (statisticForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        statisticForUpdate.setAccommodation(AccommodationMapper.toEntity(statisticsDTO.getAccommodationDto()));
        statisticForUpdate.setYear(statisticsDTO.getYear());
        List<StatisticsDetail> statisticsDetails = new ArrayList<StatisticsDetail>();
        if(statisticsDTO.getDetailsDto() != null) {
            for(StatisticsDetailDTO statisticsDetailDTO : statisticsDTO.getDetailsDto())
                statisticsDetails.add(StatisticsDetailMapper.toEntity(statisticsDetailDTO));
        }
        statisticForUpdate.setDetails(statisticsDetails);
        statisticForUpdate.setProfit(statisticsDTO.getProfit());
        statisticForUpdate.setNumberOfReservations(statisticsDTO.getNumberOfReservations());

        statisticForUpdate = service.save(statisticForUpdate);

        return new ResponseEntity<>(AccommodationStatisticsMapper.toDto(statisticForUpdate), HttpStatus.OK);
    }


    /** url: /api/accommodation-statistics/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AccommodationStatistics> deleteStatistic(@PathVariable("id") Long id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
