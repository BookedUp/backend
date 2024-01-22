package rs.ac.uns.ftn.asd.BookedUp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReservationRepository;

import javax.xml.crypto.Data;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private IReservationRepository reservationRepository;
    @MockBean
    private AccommodationService accommodationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Rollback
    public void createReservation_TestUnsuccessful(){
        Reservation reservation = new Reservation();
        reservation.setId(100L);

        Exception exception = assertThrows(Exception.class,
                () -> reservationService.create(reservation));
        assertEquals("Id mora biti null prilikom perzistencije novog entiteta.", exception.getMessage());

        verifyNoInteractions(accommodationService);
    }

    @Test
    @Rollback
    public void createReservation_TestSuccessful() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setStatus(ReservationStatus.ACCEPTED);

        Accommodation accommodation = new Accommodation();
        reservation.setAccommodation(accommodation);

        reservation.setStartDate(new Date());
        reservation.setEndDate(new Date());

        doNothing().when(accommodationService).updateAvailibility(reservation.getAccommodation(), reservation.getStartDate(), reservation.getEndDate());
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation savedRes = reservationService.create(reservation);
        assertEquals(savedRes, reservation);
        verify(accommodationService).updateAvailibility(reservation.getAccommodation(), reservation.getStartDate(), reservation.getEndDate());

    }

    @Test
    @Rollback
    public void approveReservation_TestSuccessful() throws Exception {
        Reservation reservation = new Reservation();

        when(reservationRepository.findAll()).thenReturn(new ArrayList<>());
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation savedRes = reservationService.approveReservation(reservation);
        assertEquals(ReservationStatus.ACCEPTED, savedRes.getStatus());

        verify(reservationRepository, times(2)).findAll();
        verify(reservationRepository).save(reservation);

    }

    @Test
    @Rollback
    public void rejectReservation_TestSuccessful(){
        Reservation reservation = new Reservation();

        when(reservationRepository.save(reservation)).thenReturn(reservation);

        reservationService.rejectReservation(reservation);
        assertEquals(ReservationStatus.REJECTED, reservation.getStatus());

        verify(reservationRepository).save(reservation);
    }

}
