package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.AccommodationStatistics;
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
        if(!this.validateStatisticsDTO(statisticsDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            createdStatisticsDto = statisticsService.create(statisticsDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AccommodationStatisticsDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdStatisticsDto, HttpStatus.OK);
    }

    private boolean validateStatisticsDTO(AccommodationStatisticsDTO statisticsDto) {
        return true;
    }

    /* url: /api/accommodation-statistics/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationStatisticsDTO> updateReport(@RequestBody AccommodationStatisticsDTO statisticsDTO, @PathVariable Long id)
            throws Exception {
        AccommodationStatisticsDTO statisticForUpdate = statisticsService.getById(id);
        statisticForUpdate.copyValues(statisticsDTO);

        AccommodationStatisticsDTO updatedStatistic = statisticsService.update(statisticForUpdate);

        if (updatedStatistic == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedStatistic, HttpStatus.OK);
    }

    /** url: /api/accommodation-statistics/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AccommodationStatistics> deleteReport(@PathVariable("id") Long id) {
        statisticsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
