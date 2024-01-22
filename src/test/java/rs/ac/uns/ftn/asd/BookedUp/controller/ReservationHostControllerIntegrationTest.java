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
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.LogInDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.TokenDTO;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationRepository;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration")
@ActiveProfiles("test")
@Transactional
public class ReservationHostControllerIntegrationTest {
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
    public void login() {

        // Assuming the test user's email and password
        String email = "ana.anic@example.com";
        String password = "anapass";

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
    public void approveReservation_ShouldBeApproved() throws ParseException {

        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations/" + 1L + "/confirmation",
                HttpMethod.PUT,
                new HttpEntity<>(getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {
                });
        ReservationDTO returnedReservation = responseEntity.getBody();

        // Assert HTTP Status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ReservationStatus.ACCEPTED, returnedReservation.getStatus());

    }

    @Test
    @Rollback
    public void approveReservation_ShouldReturnNotFound() throws ParseException {

        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations/" + 172L + "/confirmation",
                HttpMethod.PUT,
                new HttpEntity<>(getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {
                });

        // Assert HTTP Status
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @Test
    @Rollback
    public void approveReservation_ShouldReturnForbidden() throws ParseException {

        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations/" + 3L + "/confirmation",
                HttpMethod.PUT,
                new HttpEntity<>(getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {
                });

        // Assert HTTP Status
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());

    }

    @Test
    @Rollback
    public void rejectReservation_ShouldBeRejected() throws ParseException {

        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations/" + 2L + "/rejection",
                HttpMethod.PUT,
                new HttpEntity<>(getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {
                });
        ReservationDTO returnedReservation = responseEntity.getBody();

        // Assert HTTP Status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ReservationStatus.REJECTED, returnedReservation.getStatus());

    }

    @Test
    @Rollback
    public void rejectReservation_ShouldReturnNotFound() throws ParseException {

        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations/" + 220L + "/rejection",
                HttpMethod.PUT,
                new HttpEntity<>(getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {
                });

        // Assert HTTP Status
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @Test
    @Rollback
    public void rejectReservation_ShouldReturnForbidden() throws ParseException {

        ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange("/api/reservations/" + 3 + "/rejection",
                HttpMethod.PUT,
                new HttpEntity<>(getHttpHeaders()),
                new ParameterizedTypeReference<ReservationDTO>() {
                });

        // Assert HTTP Status
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());

    }


}
