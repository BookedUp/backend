package rs.ac.uns.ftn.asd.BookedUp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.DateRange;
import rs.ac.uns.ftn.asd.BookedUp.domain.PriceChange;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.AddressDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.DateRangeDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.PriceChangeDTO;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationRepository;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReservationRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AccommodationServiceTest {

    @Autowired
    private AccommodationService accommodationService;

    @MockBean
    private IAccommodationRepository accommodationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Rollback
    public void updateAccommodation_TestSuccessful(){
        Accommodation accommodation = new Accommodation();
        accommodation.setAvailability(new ArrayList<>());
        accommodation.setPriceChanges(new ArrayList<>());
        accommodation.setPrice(1200);
        accommodation.setCancellationDeadline(20);

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

        when(accommodationRepository.save(accommodation)).thenReturn(accommodation);

        Accommodation result = accommodationService.updateAccommodation(accommodation, accommodationDTO);
        assertEquals(accommodation.getPrice(), 250);
        assertEquals(accommodation.getCancellationDeadline(), 12);

        verify(accommodationRepository).save(accommodation);
    }
}
