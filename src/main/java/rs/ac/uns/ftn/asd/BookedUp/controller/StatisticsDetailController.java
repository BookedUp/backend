package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.StatisticsDetail;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.StatisticsDetailDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.StatisticsDetailMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.UserMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.StatisticsDetailService;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/statistics-details")
@CrossOrigin
public class StatisticsDetailController {
    @Autowired
    private StatisticsDetailService statisticsDetailService;

    /*url: /api/statistics-details GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<StatisticsDetailDTO>> getStatisticsDetails() {
        Collection<StatisticsDetail> statisticsDetails = statisticsDetailService.getAll();
        Collection<StatisticsDetailDTO> statisticsDetailsDTO = statisticsDetails.stream()
                .map(StatisticsDetailMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(statisticsDetailsDTO, HttpStatus.OK);
    }

    /* url: /api/statistics-details/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDetailDTO> getStatisticsDetail(@PathVariable("id") Long id) {
        StatisticsDetail statisticsDetail = statisticsDetailService.getById(id);

        if (statisticsDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(StatisticsDetailMapper.toDto(statisticsDetail), HttpStatus.OK);
    }

    /*url: /api/statistics-details POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDetailDTO> createStatisticsDetail(@Valid @RequestBody StatisticsDetailDTO statisticsDetailDTO) throws Exception {
        StatisticsDetail createdStatisticsDetail = null;

        try {
            createdStatisticsDetail = statisticsDetailService.create(StatisticsDetailMapper.toEntity(statisticsDetailDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatisticsDetailDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(StatisticsDetailMapper.toDto(createdStatisticsDetail), HttpStatus.CREATED);
    }

    /* url: /api/statistics-details/1 PUT*/
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDetailDTO> updateStatisticsDetail(@Valid @RequestBody StatisticsDetailDTO statisticsDetailDTO, @PathVariable Long id)
            throws Exception {
        StatisticsDetail statisticsDetailForUpdate = statisticsDetailService.getById(id);
        if (statisticsDetailForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        statisticsDetailForUpdate.setProfit(statisticsDetailDTO.getProfit());
        statisticsDetailForUpdate.setNumberOfReservations(statisticsDetailDTO.getNumberOfReservations());

        statisticsDetailForUpdate = statisticsDetailService.save(statisticsDetailForUpdate);

        return new ResponseEntity<>(StatisticsDetailMapper.toDto(statisticsDetailForUpdate), HttpStatus.OK);
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
