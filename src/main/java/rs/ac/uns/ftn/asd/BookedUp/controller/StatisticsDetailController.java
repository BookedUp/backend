package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.StatisticsDetailService;

import java.util.Collection;

@RestController
@RequestMapping("/api/statistics-details")
public class StatisticsDetailController {
    @Autowired
    private StatisticsDetailService statisticsDetailService;

    /*url: /api/statistics-details GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<StatisticsDetailDTO>> getStatisticsDetails() {
        Collection<StatisticsDetailDTO> statisticsDetailDTOS = statisticsDetailService.getAll();
        return new ResponseEntity<>(statisticsDetailDTOS, HttpStatus.OK);
    }

    /* url: /api/statistics-details/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDetailDTO> getStatisticsDetail(@PathVariable("id") Long id) {
        StatisticsDetailDTO statisticsDetailDTO = statisticsDetailService.getById(id);

        if (statisticsDetailDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(statisticsDetailDTO, HttpStatus.OK);
    }

    /*url: /api/statistics-details POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDetailDTO> createStatisticsDetail(@RequestBody StatisticsDetailDTO statisticsDetailDto) throws Exception {
        StatisticsDetailDTO createdStatisticsDetailDTO = null;
        if(!this.validateStatisticsDTO(statisticsDetailDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            createdStatisticsDetailDTO = statisticsDetailService.create(statisticsDetailDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatisticsDetailDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdStatisticsDetailDTO, HttpStatus.OK);
    }

    private boolean validateStatisticsDTO(StatisticsDetailDTO statisticsDetailDto) {
        return true;
    }

    /* url: /api/statistics-details/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDetailDTO> updateStatisticsDetail(@RequestBody StatisticsDetailDTO statisticsDetailDto, @PathVariable Long id)
            throws Exception {
        StatisticsDetailDTO statisticsDetailForUpdate = statisticsDetailService.getById(id);
        statisticsDetailForUpdate.copyValues(statisticsDetailDto);

        StatisticsDetailDTO updatedStatisticsDetail = statisticsDetailService.update(statisticsDetailForUpdate);

        if (updatedStatisticsDetail == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updatedStatisticsDetail, HttpStatus.OK);
    }

    /** url: /api/statistics-details/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StatisticsDetailDTO> deleteStatisticsDetail(@PathVariable("id") Long id) {
        statisticsDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
