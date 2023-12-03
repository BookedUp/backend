package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationStatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationStatisticsService;

import java.util.Collection;

@RestController
@RequestMapping("/api/accommodation-report")
public class AccommodationStatisticsController {
    @Autowired
    private AccommodationStatisticsService statisticsService;

    /*url: /api/accommodation-statistics GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AccommodationStatisticsDTO>> getStatistics() {
        Collection<AccommodationStatisticsDTO> statisticsDTOS = statisticsService.getAll();
        if (statisticsDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statisticsDTOS, HttpStatus.OK);
    }

    /* url: /api/accommodation-statistics/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationStatisticsDTO> getStatistic(@PathVariable("id") Long id) {
        AccommodationStatisticsDTO statisticsDTO = statisticsService.getById(id);

        if (statisticsDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(statisticsDTO, HttpStatus.OK);
    }

    /*url: /api/accommodation-statistics POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationStatisticsDTO> createStatistic(@RequestBody AccommodationStatisticsDTO statisticsDto) throws Exception {
        AccommodationStatisticsDTO createdStatisticsDto = null;
        try {
            createdStatisticsDto = statisticsService.create(statisticsDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AccommodationStatisticsDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdStatisticsDto, HttpStatus.CREATED);
    }

    private boolean validateStatisticsDTO(AccommodationStatisticsDTO statisticsDto) {
        return true;
    }

    /* url: /api/accommodation-statistics/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationStatisticsDTO> updateStatistic(@Valid @RequestBody AccommodationStatisticsDTO statisticsDTO, @PathVariable Long id)
            throws Exception {
        AccommodationStatisticsDTO statisticForUpdate = statisticsService.getById(id);
        //resurs za azuriranje nije pronadjen
        if (statisticForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        statisticForUpdate.copyValues(statisticsDTO);
        AccommodationStatisticsDTO updatedStatistic = statisticsService.update(statisticForUpdate);

        if (updatedStatistic == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedStatistic, HttpStatus.OK);
    }


    /** url: /api/accommodation-statistics/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AccommodationStatistics> deleteStatistic(@PathVariable("id") Long id) {
        try {
            statisticsService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
