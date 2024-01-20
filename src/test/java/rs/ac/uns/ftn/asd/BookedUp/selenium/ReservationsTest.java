package rs.ac.uns.ftn.asd.BookedUp.selenium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.pages.IndexPage;
import rs.ac.uns.ftn.asd.BookedUp.pages.LoginPage;
import rs.ac.uns.ftn.asd.BookedUp.pages.ReservationRequestsPage;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationService;
import rs.ac.uns.ftn.asd.BookedUp.service.ReservationService;
import rs.ac.uns.ftn.asd.BookedUp.service.UserService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.testng.Assert.assertTrue;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class ReservationsTest extends TestBase {
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private AccommodationService accommodationService;


    @Test
    @DisplayName("#01-Test: View and Cancel Reservation - Host")
    @Sql("classpath:data.sql")
    @DirtiesContext
    public void testViewAndCancelReservationHost() {
        //Login
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());
        loginPage.inputUsername("ana.anic@example.com");
        loginPage.inputPassword("anapass");
        loginPage.clickLoginBtn();

        //Index
        IndexPage indexPage = new IndexPage(driver);
        assertTrue(indexPage.isPageOpened());
        indexPage.openMenuHost();
        indexPage.selectReservationsHost();

        //ReservationRequests
        ReservationRequestsPage reservationRequestsPage = new ReservationRequestsPage(driver);
        assertTrue(reservationRequestsPage.isPageOpened());
        reservationRequestsPage.clickOnWaitingForApproval();
        reservationRequestsPage.acceptReservation();
        assertTrue(reservationRequestsPage.isReservationSuccessfullyAccepted());
        reservationRequestsPage.clickConfirmButton();
        reservationRequestsPage.clickLogo();

        //Index
        IndexPage indexPage1 = new IndexPage(driver);
        assertTrue(indexPage1.isPageOpened());
        indexPage1.openMenuHost();
        indexPage1.logoutHost();


    }

    @Test
    @DisplayName("#01-Test: View and Cancel Reservation - Guest")
    @Sql("classpath:data.sql")
    @DirtiesContext
    public void testViewAndCancelReservationGuest() {
        //Login
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());
        loginPage.inputUsername("jovana.jovanovic@example.com");
        loginPage.inputPassword("jovanapass");
        loginPage.clickLoginBtn();

        //Index
        IndexPage indexPage = new IndexPage(driver);
        assertTrue(indexPage.isPageOpened());
        indexPage.openMenuHost();
        indexPage.selectReservationsHost();

        //ReservationRequests
        ReservationRequestsPage reservationRequestsPage = new ReservationRequestsPage(driver);
        assertTrue(reservationRequestsPage.isPageOpened());
        reservationRequestsPage.clickOnWaitingForApproval();
        reservationRequestsPage.acceptReservation();
        assertTrue(reservationRequestsPage.isReservationSuccessfullyAccepted());
        reservationRequestsPage.clickConfirmButton();
        reservationRequestsPage.clickLogo();

        //Index
        IndexPage indexPage1 = new IndexPage(driver);
        assertTrue(indexPage1.isPageOpened());
        indexPage1.openMenuHost();
        indexPage1.logoutHost();


    }



    private int getNumberOfCanceledReservationsFromDatabase(UserDTO user) {
        int numberOfCanceledReservations = 0;
        for (Reservation reservation : reservationService.getReservationsByGuestId(user.getId())) {
            if (reservation.getStatus().equals(ReservationStatus.CANCELLED)) {
                numberOfCanceledReservations++;
            }
        }
        System.out.println(numberOfCanceledReservations);
        return numberOfCanceledReservations;
    }
}
