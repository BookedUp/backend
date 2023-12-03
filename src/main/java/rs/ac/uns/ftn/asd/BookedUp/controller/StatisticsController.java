package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Statistics;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.StatisticsService;

import java.util.Collection;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    /*url: /api/statistics GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<StatisticsDTO>> getStatistics() {
        Collection<StatisticsDTO> statisticsDTOS = statisticsService.getAll();
        if (statisticsDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statisticsDTOS, HttpStatus.OK);
    }

    /* url: /api/statistics/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDTO> getStatistics(@PathVariable("id") Long id) {
        StatisticsDTO statisticsDTO = statisticsService.getById(id);

        if (statisticsDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(statisticsDTO, HttpStatus.OK);
    }

    /*url: /api/statistics POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDTO> createStatistics(@Valid @RequestBody StatisticsDTO statisticsDto) throws Exception {
        StatisticsDTO createdStatisticsDto = null;

        try {
            createdStatisticsDto = statisticsService.create(statisticsDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatisticsDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdStatisticsDto, HttpStatus.CREATED);
    }

    private boolean validateStatisticsDTO(StatisticsDTO statisticsDto) {
        return true;
    }

    /* url: /api/statistics/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDTO> updateStatistics(@Valid @RequestBody StatisticsDTO statisticsDto, @PathVariable Long id)
            throws Exception {
        StatisticsDTO statisticsForUpdate = statisticsService.getById(id);
        if (statisticsForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        statisticsForUpdate.copyValues(statisticsDto);
        StatisticsDTO updatedStatistics = statisticsService.update(statisticsForUpdate);

        if (updatedStatistics == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedStatistics, HttpStatus.OK);
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
