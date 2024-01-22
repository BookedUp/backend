package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.*;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationRepository;

import java.text.ParseException;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration")
@ActiveProfiles("test")
@Transactional
public class ReservationGuestControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IAccommodationRepository accommodationRepository;

    private String token;

    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        return headers;
    }

    @BeforeEach
    public void loginHost() {

        // Assuming the test user's email and password
        String email = "mila.milicevic@example.com";
        String password = "milinpass";

        LogInDTO logInDTO = new LogInDTO(email, password);

        // Perform the login request
        ResponseEntity<TokenDTO> responseEntity = restTemplate.exchange("/api/login",
                HttpMethod.POST,
                new HttpEntity<>(logInDTO),
                new ParameterizedTypeReference<TokenDTO>() {
                });

        // Validate the response
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getToken());

        System.out.println("TOKEN " + responseEntity.getBody().getToken());
        this.token = "Bearer " + responseEntity.getBody().getToken();

    }

    @Test
    @Rollback
    public void createReservation_ShouldCreatePending() throws ParseException {
        AccommodationDTO accommodationDTO = new AccommodationDTO();
        accommodationDTO.setId(4L);

        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setId(7L);

        ReservationStatus status = ReservationStatus.CREATED;
        ReservationDTO reservationDTO = new ReservationDTO(new Date(), new Date(), 1000.0, 2,accommodationDTO, guestDTO, status);

        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations",
                HttpMethod.POST,
                new HttpEntity<>(reservationDTO, getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {});

        System.out.println("Reservation" + reservationDTO.toString());

        // Assert HTTP Status
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Assert returned ReservationDTO
        ReservationDTO returnedReservation = responseEntity.getBody();
        assertThat(reservationDTO).usingRecursiveComparison().ignoringFields("id", "reservationStatus").isEqualTo(returnedReservation);

        // Assert that the reservation status is set to Pending
        assertEquals(ReservationStatus.CREATED, returnedReservation.getStatus());
    }

    @Test
    @Rollback
    public void createReservation_ShouldCreateAccepted() throws ParseException {
        Accommodation accommodation = accommodationRepository.findById(1L).orElse(null);
        AccommodationDTO accommodationDTO = AccommodationMapper.toDto(accommodation);


        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setId(7L);

        ReservationStatus status = ReservationStatus.ACCEPTED;
        ReservationDTO reservationDTO = new ReservationDTO(new Date(), new Date(), 1000.0, 2,accommodationDTO, guestDTO, status);

        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations",
                HttpMethod.POST,
                new HttpEntity<>(reservationDTO, getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {});

        System.out.println("Reservation" + reservationDTO.toString());

        // Assert HTTP Status
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Assert returned ReservationDTO
        ReservationDTO returnedReservation = responseEntity.getBody();
        // Assert that the reservation status is set to Accepted
        assertEquals(ReservationStatus.ACCEPTED, returnedReservation.getStatus());
    }

    @Test
    @Rollback
    public void createReservation_ShouldReturnBadRequest() throws ParseException {
        AccommodationDTO accommodationDTO = new AccommodationDTO();
        accommodationDTO.setId(4L);

        GuestDTO guestDTO = new GuestDTO();

        ReservationDTO reservationDTO = new ReservationDTO(new Date(), new Date(), 1000.0, 2,accommodationDTO, guestDTO, ReservationStatus.CREATED);

        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations",
                HttpMethod.POST,
                new HttpEntity<>(reservationDTO, getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {});

        System.out.println("Reservation" + reservationDTO.toString());

        // Assert HTTP Status
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }



}

