package rs.ac.uns.ftn.asd.BookedUp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.PriceType;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.AddressDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.DateRangeDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.PriceChangeDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.DateRangeMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.PriceChangeMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationRepository;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReservationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AccommodationServiceTest {

    @Autowired
    private AccommodationService accommodationService;

    @MockBean
    private IAccommodationRepository accommodationRepository;

    @MockBean
    private IReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Rollback
    public void updateAccommodation_TestSuccessful_WithActiveReservations(){
        Accommodation accommodation = new Accommodation();

        AccommodationDTO accommodationDTO = new AccommodationDTO();
        accommodationDTO.setAddress(new AddressDTO());

        Guest guest = new Guest();
        guest.setId(4L);
        Reservation reservation1 = new Reservation(LocalDateTime.now(), new Date(), new Date(), 300, 3, accommodation, guest, ReservationStatus.CREATED, true);
        Reservation reservation2 = new Reservation(LocalDateTime.now(), new Date(), new Date(), 200, 2, accommodation, guest, ReservationStatus.ACCEPTED, true);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);

        when(reservationRepository.findAllByAccommodationId(100L)).thenReturn(reservations);
        Boolean hasActive = accommodationService.hasActiveReservations(100L);
        assertEquals(true, hasActive);

       verifyNoInteractions(accommodationRepository);
    }

    @Test
    @Rollback
    public void updateAccommodation_TestSuccessful_WithoutActiveReservations(){
        Accommodation accommodation = new Accommodation();

        AccommodationDTO accommodationDTO = new AccommodationDTO();

        DateRangeDTO dateRange = new DateRangeDTO();
        dateRange.setStartDate(new Date());
        dateRange.setEndDate(new Date());

        List<DateRangeDTO> availability = new ArrayList<>();
        availability.add(dateRange);

        accommodationDTO.setAvailability(availability);

        PriceChangeDTO priceChangeDTO = new PriceChangeDTO();
        priceChangeDTO.setChangeDate(new Date());
        priceChangeDTO.setNewPrice(500);

        List<PriceChangeDTO> priceChanges = new ArrayList<>();
        priceChanges.add(priceChangeDTO);

        accommodationDTO.setPriceChanges(priceChanges);
        accommodationDTO.setPrice(250);
        accommodationDTO.setCancellationDeadline(12);
        accommodationDTO.setAddress(new AddressDTO());

        Guest guest = new Guest();
        guest.setId(4L);
        Reservation reservation1 = new Reservation(LocalDateTime.now(), new Date(), new Date(), 300, 3, accommodation, guest, ReservationStatus.COMPLETED, true);
        Reservation reservation2 = new Reservation(LocalDateTime.now(), new Date(), new Date(), 200, 2, accommodation, guest, ReservationStatus.COMPLETED, true);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);

        when(reservationRepository.findAllByAccommodationId(100L)).thenReturn(reservations);
        Boolean hasActive = accommodationService.hasActiveReservations(100L);
        assertEquals(false, hasActive);

        when(accommodationRepository.save(accommodation)).thenReturn(accommodation);

        Accommodation result = accommodationService.updateAccommodation(accommodation, accommodationDTO);

        List<DateRangeDTO> dateRangeDTOS = result.getAvailability().stream()
                .map(DateRangeMapper::toDto)
                .collect(Collectors.toList());

        List<PriceChangeDTO> priceChangeDTOS = result.getPriceChanges().stream()
                .map(PriceChangeMapper::toDto)
                .collect(Collectors.toList());

        assertEquals(accommodation.getPrice(), 250);
        assertEquals(accommodation.getCancellationDeadline(), 12);
        assertIterableEquals(accommodationDTO.getAvailability(), dateRangeDTOS);
        assertIterableEquals(accommodationDTO.getPriceChanges(), priceChangeDTOS);

        verify(accommodationRepository).save(accommodation);
    }

}
