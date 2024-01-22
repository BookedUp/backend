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
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.LogInDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.TokenDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AccommodationMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.IAccommodationRepository;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration")
@ActiveProfiles("test")
@Transactional
public class AccommodationControllerIntegrationTest {

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
    public void updateAccommodationTest_ShouldBeUpdated(){
        Accommodation accommodation = accommodationRepository.findById(9L).orElse(null);
        AccommodationDTO accommodationDTO = AccommodationMapper.toDto(accommodation);

        accommodationDTO.setAvailability(new ArrayList<>());
        accommodationDTO.setCancellationDeadline(15);
        accommodationDTO.setPriceChanges(new ArrayList<>());
        accommodationDTO.setPrice(500.0);

        ResponseEntity<AccommodationDTO> responseEntity = restTemplate.exchange("/api/accommodations/" + 9,
                HttpMethod.PUT,
                new HttpEntity<>(accommodationDTO, getHttpHeaders()),
                new ParameterizedTypeReference<AccommodationDTO>() {
                });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(500, responseEntity.getBody().getPrice());
        assertEquals(15, responseEntity.getBody().getCancellationDeadline());
        assertThat(responseEntity.getBody().getAvailability().isEmpty());

    }

    @Test
    @Rollback
    public void updateAccommodationTest_ShouldReturnNotFound(){

        ResponseEntity<AccommodationDTO> responseEntity = restTemplate.exchange("/api/accommodations/" + 229,
                HttpMethod.PUT,
                new HttpEntity<>(new AccommodationDTO(), getHttpHeaders()),
                new ParameterizedTypeReference<AccommodationDTO>() {
                });

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @Rollback
    public void updateAccommodationTest_ShouldReturnForbidden(){
        Accommodation accommodation = accommodationRepository.findById(5L).orElse(null);
        AccommodationDTO accommodationDTO = AccommodationMapper.toDto(accommodation);

        ResponseEntity<AccommodationDTO> responseEntity = restTemplate.exchange("/api/accommodations/" + 5,
                HttpMethod.PUT,
                new HttpEntity<>(accommodationDTO, getHttpHeaders()),
                new ParameterizedTypeReference<AccommodationDTO>() {
                });

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());

    }
}
