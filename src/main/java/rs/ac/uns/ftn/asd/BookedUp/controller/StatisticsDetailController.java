package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
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
        if (statisticsDetailDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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
    public ResponseEntity<StatisticsDetailDTO> createStatisticsDetail(@Valid @RequestBody StatisticsDetailDTO statisticsDetailDto) throws Exception {
        StatisticsDetailDTO createdStatisticsDetailDTO = null;

        try {
            createdStatisticsDetailDTO = statisticsDetailService.create(statisticsDetailDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatisticsDetailDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdStatisticsDetailDTO, HttpStatus.CREATED);
    }

    private boolean validateStatisticsDTO(StatisticsDetailDTO statisticsDetailDto) {
        return true;
    }

    /* url: /api/statistics-details/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDetailDTO> updateStatisticsDetail(@Valid @RequestBody StatisticsDetailDTO statisticsDetailDto, @PathVariable Long id)
            throws Exception {
        StatisticsDetailDTO statisticsDetailForUpdate = statisticsDetailService.getById(id);
        if (statisticsDetailForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        statisticsDetailForUpdate.copyValues(statisticsDetailDto);
        StatisticsDetailDTO updatedStatisticsDetail = statisticsDetailService.update(statisticsDetailForUpdate);

        if (updatedStatisticsDetail == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updatedStatisticsDetail, HttpStatus.OK);
    }

    /** url: /api/statistics-details/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StatisticsDetailDTO> deleteStatisticsDetail(@PathVariable("id") Long id) {
        try {
            statisticsDetailService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
