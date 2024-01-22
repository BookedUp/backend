package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ReservationRepositoryTest {

    @Autowired
    private IReservationRepository reservationRepository;
    @Autowired
    private IAccommodationRepository accommodationRepository;

    @Test
    public void shouldFindAllReservationsByAccommodationId_HasActiveReservations() {
        Accommodation accommodation = new Accommodation();
        accommodation.setId(100L);
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);

        Guest guest = new Guest();
        guest.setId(4L);
        Reservation reservation1 = new Reservation(LocalDateTime.now(), new Date(), new Date(), 300, 3, accommodation, guest, ReservationStatus.CREATED, true);
        Reservation reservation2 = new Reservation(LocalDateTime.now(), new Date(), new Date(), 200, 2, accommodation, guest, ReservationStatus.ACCEPTED, true);

        Reservation savedReservation1 = reservationRepository.save(reservation1);
        Reservation savedReservation2 = reservationRepository.save(reservation2);

        List<Reservation> reservations = reservationRepository.findAllByAccommodationId(savedAccommodation.getId());

        Assertions.assertThat(savedReservation1).usingRecursiveComparison().ignoringFields("id").isEqualTo(reservation1);
        Assertions.assertThat(savedReservation2).usingRecursiveComparison().ignoringFields("id").isEqualTo(reservation2);
        assertEquals(2, reservations.size());
    }

    @Test
    public void shouldFindAllReservationsByAccommodationId_NoActiveReservations() {
        Accommodation accommodation = new Accommodation();
        accommodation.setId(100L);
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);

        Guest guest = new Guest();
        guest.setId(4L);
        Reservation reservation1 = new Reservation(LocalDateTime.now(), new Date(), new Date(), 300, 3, accommodation, guest, ReservationStatus.COMPLETED, true);
        Reservation reservation2 = new Reservation(LocalDateTime.now(), new Date(), new Date(), 200, 2, accommodation, guest, ReservationStatus.COMPLETED, true);

        Reservation savedReservation1 = reservationRepository.save(reservation1);
        Reservation savedReservation2 = reservationRepository.save(reservation2);

        List<Reservation> reservations = reservationRepository.findAllByAccommodationId(savedAccommodation.getId());

        Assertions.assertThat(savedReservation1).usingRecursiveComparison().ignoringFields("id").isEqualTo(reservation1);
        Assertions.assertThat(savedReservation2).usingRecursiveComparison().ignoringFields("id").isEqualTo(reservation2);
        assertEquals(2, reservations.size());
    }
}
