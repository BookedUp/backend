package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.StatisticsDetailMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.StatisticsMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.UserMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.StatisticsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    /*url: /api/statistics GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<StatisticsDTO>> getStatistics() {
        Collection<Statistics> statistics = statisticsService.getAll();
        Collection<StatisticsDTO> statisticsDTO = statistics.stream()
                .map(StatisticsMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(statisticsDTO, HttpStatus.OK);
    }

    /* url: /api/statistics/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDTO> getStatistics(@PathVariable("id") Long id) {
        Statistics statistics = statisticsService.getById(id);

        if (statistics == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(StatisticsMapper.toDto(statistics), HttpStatus.OK);
    }

    /*url: /api/statistics POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDTO> createStatistics(@Valid @RequestBody StatisticsDTO statisticsDTO) throws Exception {
        Statistics createdStatistics = null;

        try {
            createdStatistics = statisticsService.create(StatisticsMapper.toEntity(statisticsDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatisticsDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(StatisticsMapper.toDto(createdStatistics), HttpStatus.CREATED);
    }

    /* url: /api/statistics/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDTO> updateStatistics(@Valid @RequestBody StatisticsDTO statisticsDTO, @PathVariable Long id)
            throws Exception {
        Statistics statisticsForUpdate = statisticsService.getById(id);
        if (statisticsForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        statisticsForUpdate.setFromDate(statisticsDTO.getFromDate());
        statisticsForUpdate.setToDate(statisticsDTO.getToDate());
        List<StatisticsDetail> statisticsDetails = new ArrayList<StatisticsDetail>();
        if(statisticsDTO.getDetails() != null) {
            for(StatisticsDetailDTO statisticsDetailDTO : statisticsDTO.getDetails())
                statisticsDetails.add(StatisticsDetailMapper.toEntity(statisticsDetailDTO));
        }
        statisticsForUpdate.setDetails(statisticsDetails);
        statisticsForUpdate.setProfit(statisticsDTO.getProfit());
        statisticsForUpdate.setNumberOfReservations(statisticsDTO.getNumberOfReservations());

        statisticsForUpdate = statisticsService.save(statisticsForUpdate);

        return new ResponseEntity<>(StatisticsMapper.toDto(statisticsForUpdate), HttpStatus.OK);
    }

    /** url: /api/statistics/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Statistics> deleteStatistics(@PathVariable("id") Long id) {
        try {
            statisticsService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
