package rs.ac.uns.ftn.asd.BookedUp.service;

import com.beust.ah.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.repository.IReservationRepository;

import javax.xml.crypto.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    @MockBean
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Rollback
    public void createReservation_TestUnsuccessful_IdReservationAlreadyExist(){    //kada je poslata rezervacija sa postojecim id-jem

        Reservation reservation = new Reservation();
        reservation.setId(100L);

        List<Reservation> existedReservations = new ArrayList<>();

        Exception exception = assertThrows(Exception.class,
                () -> reservationService.create(reservation));
        assertEquals("Id mora biti null prilikom perzistencije novog entiteta.", exception.getMessage());

        verifyNoInteractions(accommodationService);
        verifyNoInteractions(notificationService);

        verify(reservationRepository, times(1)).findAll();
        verify(reservationRepository, times(1)).saveAll(existedReservations);
    }

    @Test
    @Rollback
    public void createReservationAndCreateNotification_TestSuccessful_AutomaticAcceptationTurnOn() throws Exception {
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
        verify(notificationService, atLeastOnce()).create(any(Notification.class));
    }

    @Test
    @Rollback
    public void createReservationAndCreateNotification_TestSuccessful_AutomaticAcceptationTurnOff() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setStatus(ReservationStatus.CREATED);

        Accommodation accommodation = new Accommodation();
        reservation.setAccommodation(accommodation);

        reservation.setStartDate(new Date());
        reservation.setEndDate(new Date());

        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation savedRes = reservationService.create(reservation);
        assertEquals(savedRes, reservation);

        verifyNoInteractions(accommodationService);
        verify(notificationService, times(1)).create(any(Notification.class));
    }

    @Test
    @Rollback
    public void approveReservationAndCreateNotification_TestSuccessful_AllReservationsListIsEmpty() throws Exception {
        Reservation reservation = new Reservation();
        Accommodation accommodation = new Accommodation();

        reservation.setAccommodation(accommodation);
        reservation.setStartDate(new Date());
        reservation.setEndDate(new Date());

        when(reservationRepository.findAll()).thenReturn(new ArrayList<>());
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation savedRes = reservationService.approveReservation(reservation);
        assertEquals(ReservationStatus.ACCEPTED, savedRes.getStatus());

        verify(reservationRepository, atLeastOnce()).findAll();
        verify(accommodationService, times(1)).updateAvailibility(reservation.getAccommodation(), reservation.getStartDate(), reservation.getEndDate());
        verify(reservationRepository).save(reservation);
        verify(notificationService, times(1)).create(any(Notification.class));
    }

    @Test
    @Rollback
    public void approveReservationAndCreateNotification_TestSuccessful_NoOverlappingReservations() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        Date newResStartDate = dateFormat.parse("2023-12-11 13:00:00.000000");
        Date newResEndDate = dateFormat.parse("2024-02-15 13:00:00.000000");

        Date exResStartDate = dateFormat.parse("2023-02-23 13:00:00.000000");
        Date exResEndDate = dateFormat.parse("2024-02-27 13:00:00.000000");

        Reservation existingReservation = new Reservation();
        existingReservation.setStartDate(exResStartDate);
        existingReservation.setEndDate(exResEndDate);
        existingReservation.setStatus(ReservationStatus.CREATED);

        Reservation reservation = new Reservation();
        Accommodation accommodation = new Accommodation();
        reservation.setAccommodation(accommodation);
        reservation.setStartDate(newResStartDate);
        reservation.setEndDate(newResEndDate);
        reservation.setStatus(ReservationStatus.CREATED);

        List<Reservation> allReservations = new ArrayList<>();
        allReservations.add(reservation);
        allReservations.add(existingReservation);

        when(reservationRepository.findAll()).thenReturn(allReservations);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation savedRes = reservationService.approveReservation(reservation);
        assertEquals(ReservationStatus.ACCEPTED, savedRes.getStatus());
        assertNotEquals(ReservationStatus.REJECTED, existingReservation.getStatus());

        verify(reservationRepository, atLeastOnce()).findAll();
        verify(accommodationService, times(1)).updateAvailibility(reservation.getAccommodation(), reservation.getStartDate(), reservation.getEndDate());
        verify(reservationRepository).save(reservation);
        verify(notificationService, times(1)).create(any(Notification.class));
    }


    @Test
    @Rollback
    public void approveReservationAndCreateNotification_TestSuccessful_ExistingOverlappingReservations() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");

        Date date1 = dateFormat.parse("2023-12-11 13:00:00.000000");
        Date date2 = dateFormat.parse("2024-02-15 13:00:00.000000");

        Date exResStartDate = dateFormat.parse("2023-12-23 13:00:00.000000");
        Date exResEndDate = dateFormat.parse("2024-02-15 13:00:00.000000");

        Reservation existingReservation = new Reservation();
        existingReservation.setStartDate(exResStartDate);
        existingReservation.setEndDate(exResEndDate);
        existingReservation.setStatus(ReservationStatus.CREATED);

        Reservation reservation = new Reservation();
        Accommodation accommodation = new Accommodation();
        reservation.setAccommodation(accommodation);
        reservation.setStartDate(date1);
        reservation.setEndDate(date2);
        reservation.setStatus(ReservationStatus.CREATED);

        List<Reservation> allReservations = new ArrayList<>();
        allReservations.add(reservation);
        allReservations.add(existingReservation);

        when(reservationRepository.findAll()).thenReturn(allReservations);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation savedRes = reservationService.approveReservation(reservation);
        assertEquals(ReservationStatus.ACCEPTED, savedRes.getStatus());
        assertEquals(ReservationStatus.REJECTED, existingReservation.getStatus());

        verify(reservationRepository, atLeastOnce()).findAll();
        verify(accommodationService, times(1)).updateAvailibility(reservation.getAccommodation(), reservation.getStartDate(), reservation.getEndDate());
        verify(notificationService, atLeastOnce()).create(any(Notification.class)); //je l ok da bude 3 puta
        verify(reservationRepository, times(1)).save(reservation);
        verify(reservationRepository, times(1)).save(existingReservation);
    }

    @Test
    @Rollback
    public void rejectReservation_TestSuccessful() throws Exception {
        Reservation reservation = new Reservation();

        when(reservationRepository.save(reservation)).thenReturn(reservation);

        reservationService.rejectReservation(reservation);
        assertEquals(ReservationStatus.REJECTED, reservation.getStatus());

        verify(reservationRepository).save(reservation);
        verify(notificationService, times(1)).create(any(Notification.class));
    }

}
