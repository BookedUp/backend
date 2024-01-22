package rs.ac.uns.ftn.asd.BookedUp.selenium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.pages.*;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class ReservationsTest extends TestBase {

    @Test
    @DisplayName("#01-Test: Search And Filter Accommodations")
    public void testViewAndApproveReservationHost() {
        //Login
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());
        loginPage.inputUsername("ana.anic@example.com");
        loginPage.inputPassword("anapass");
        loginPage.clickLoginBtn();

        //Index
        IndexPage indexPage = new IndexPage(driver,true);
        assertTrue(indexPage.isPageOpened());
        indexPage.openMenuHost();
        indexPage.selectReservationsHost();

        //ReservationRequests
        ReservationRequestsPage reservationRequestsPage = new ReservationRequestsPage(driver);
        assertTrue(reservationRequestsPage.isPageOpened());

        int initialReservationCountOnPage = reservationRequestsPage.getNumberOfReservations();
        assertEquals(initialReservationCountOnPage, 26, "Number of reservations on the ReservationRequestsPage is not as expected");

        reservationRequestsPage.clickOnWaitingForApproval();

        initialReservationCountOnPage = reservationRequestsPage.getNumberOfReservations();
        assertEquals(initialReservationCountOnPage, 11, "Number of reservations on the ReservationRequestsPage is not as expected");

        reservationRequestsPage.acceptReservation();
        assertTrue(reservationRequestsPage.isReservationSuccessfullyAccepted());
        reservationRequestsPage.clickConfirmButton();

//        List<Reservation> overlappingReservations = reservationService.getOverlappingReservations(hostId, ReservationStatus.WAITING_FOR_APPROVAL, acceptedReservation);
//        assertEquals(overlappingReservations.size(), 2, "Number of overlapping reservations is not as expected");
//
//        for (Reservation overlappingReservation : overlappingReservations) {
//            assertEquals(overlappingReservation.getStatus(), ReservationStatus.REJECTED, "Overlapping reservation was not automatically rejected");
//        }

        reservationRequestsPage.clickOnWaitingForApproval();

        initialReservationCountOnPage = reservationRequestsPage.getNumberOfReservations();
        assertEquals(initialReservationCountOnPage, 7, "Number of reservations on the ReservationRequestsPage is not as expected");

        reservationRequestsPage.clickLogo();

        //Index
        IndexPage indexPage1 = new IndexPage(driver,true);
        assertTrue(indexPage1.isPageOpened());
        indexPage1.openMenuHost();
        indexPage1.logoutHost();

        System.out.println("Finished test: Host Approve Reservation");


    }

    @Test
    @DisplayName("#02-Test: View and Cancel Reservation - Guest")
    public void testViewAndCancelReservationGuest() {
        //Login
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());
        loginPage.inputUsername("marko.markovic@example.com");
        loginPage.inputPassword("markopass");
        loginPage.clickLoginBtn();

        //Index
        IndexPage indexPage = new IndexPage(driver,true);
        assertTrue(indexPage.isPageOpened());
        indexPage.openMenuHost();
        indexPage.selectReservationsHost();

        //Reservations
        ReservationsPage reservationsPage = new ReservationsPage(driver);
        assertTrue(reservationsPage.isPageOpened());

        int initialReservationCountOnPage = reservationsPage.getNumberOfReservations();
        assertEquals(initialReservationCountOnPage, 30, "Number of reservations on the ReservationRequestsPage is not as expected");

        reservationsPage.clickOnCancelled();
        initialReservationCountOnPage = reservationsPage.getNumberOfReservations();
        assertEquals(initialReservationCountOnPage, 3, "Number of reservations on the ReservationRequestsPage is not as expected");

        reservationsPage.clickOnAccepted();
        initialReservationCountOnPage = reservationsPage.getNumberOfReservations();
        assertEquals(initialReservationCountOnPage, 5, "Number of reservations on the ReservationRequestsPage is not as expected");

        reservationsPage.clickViewDetailsButton(0);
        //ReservationDetailsPage
        ReservationDetailsPage reservationDetailsPage2 = new ReservationDetailsPage(driver);
        assertTrue(reservationDetailsPage2.isPageOpened());
        assertTrue(reservationDetailsPage2.isCancelVisible(), "Cancel button is not visible in details");
        reservationDetailsPage2.clickCancelButton();
        reservationDetailsPage2.clickOk();

        //Reservations
        ReservationsPage reservationsPage2 = new ReservationsPage(driver);
        assertTrue(reservationsPage2.isPageOpened());

        reservationsPage2.clickOnCancelled1();
        initialReservationCountOnPage = reservationsPage2.getNumberOfReservations();
        assertEquals(initialReservationCountOnPage, 4, "Number of reservations on the ReservationRequestsPage is not as expected");
        reservationsPage2.clickLogo();

//
        //Index
        IndexPage indexPage2 = new IndexPage(driver,true);
        assertTrue(indexPage2.isPageOpened());
        indexPage2.openMenuHost();
        indexPage2.logoutHost();

        System.out.println("Finished test: Host Approve Reservation");


    }


    @Test
    @DisplayName("#02-Test: View and Cancel Reservation OutDeadline - Guest")
    public void testViewAndCancelReservationGuestOutDeadline() {
        //Login
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isPageOpened());
        loginPage.inputUsername("marko.markovic@example.com");
        loginPage.inputPassword("markopass");
        loginPage.clickLoginBtn();

        //Index
        IndexPage indexPage = new IndexPage(driver,true);
        assertTrue(indexPage.isPageOpened());
        indexPage.openMenuHost();
        indexPage.selectReservationsHost();

        //Reservations
        ReservationsPage reservationsPage = new ReservationsPage(driver);
        assertTrue(reservationsPage.isPageOpened());

        int initialReservationCountOnPage = reservationsPage.getNumberOfReservations();
        assertEquals(initialReservationCountOnPage, 30, "Number of reservations on the ReservationRequestsPage is not as expected");

        reservationsPage.clickOnAccepted();
        initialReservationCountOnPage = reservationsPage.getNumberOfReservations();
        assertEquals(initialReservationCountOnPage, 4, "Number of reservations on the ReservationRequestsPage is not as expected");

        reservationsPage.clickViewDetailsButton(3);

        //ReservationDetailsPage
        ReservationDetailsPage reservationDetailsPage = new ReservationDetailsPage(driver);
        assertTrue(reservationDetailsPage.isPageOpened());
        assertFalse(reservationDetailsPage.isCancelVisible(), "Cancel button is not visible in details");
        reservationDetailsPage.clickLogo();
//
//        //Index
//        IndexPage indexPage1 = new IndexPage(driver,true);
//        assertTrue(indexPage1.isPageOpened());
//        indexPage1.openMenuHost();
//        indexPage1.selectReservationsHost();
//
//        //Reservations
//        ReservationsPage reservationsPage1 = new ReservationsPage(driver);
//        assertTrue(reservationsPage1.isPageOpened());
//
//        reservationsPage1.clickOnAccepted();
//        initialReservationCountOnPage = reservationsPage1.getNumberOfReservations();
//        assertEquals(initialReservationCountOnPage, 6, "Number of reservations on the ReservationRequestsPage is not as expected");
//
//        reservationsPage.clickViewDetailsButton(0);
//        //ReservationDetailsPage
//        ReservationDetailsPage reservationDetailsPage2 = new ReservationDetailsPage(driver);
//        assertTrue(reservationDetailsPage2.isPageOpened());
//        assertTrue(reservationDetailsPage2.isCancelVisible(), "Cancel button is not visible in details");
//        reservationDetailsPage2.clickCancelButton();
//        reservationDetailsPage2.clickOk();
//
//        //Reservations
//        ReservationsPage reservationsPage2 = new ReservationsPage(driver);
//        assertTrue(reservationsPage2.isPageOpened());
//
//        reservationsPage2.clickOnCancelled1();
//        initialReservationCountOnPage = reservationsPage2.getNumberOfReservations();
//        assertEquals(initialReservationCountOnPage, 4, "Number of reservations on the ReservationRequestsPage is not as expected");
//        reservationsPage2.clickLogo();

//
        //Index
        IndexPage indexPage2 = new IndexPage(driver,true);
        assertTrue(indexPage2.isPageOpened());
        indexPage2.openMenuHost();
        indexPage2.logoutHost();

        System.out.println("Finished test: Host Approve Reservation");


    }
}
