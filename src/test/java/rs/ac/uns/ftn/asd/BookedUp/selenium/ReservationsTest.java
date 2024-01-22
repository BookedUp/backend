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
import static org.testng.Assert.*;


public class ReservationsTest extends TestBase {

    @Test
    @DisplayName("#01-Test: View and Cancel Reservation - Guest")
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


        int initAcceptedReservationCountOnPage = reservationsPage.getNumberOfAcceptedReservations();
        int initCancelledReservationCountOnPage = reservationsPage.getNumberOfCancelledReservations();

        System.out.println(initAcceptedReservationCountOnPage + " " + initCancelledReservationCountOnPage);

        reservationsPage.clickOnAccepted();
        int initialReservationCountOnPage = reservationsPage.getNumberOfReservations(initAcceptedReservationCountOnPage);
        assertEquals(initialReservationCountOnPage, initAcceptedReservationCountOnPage, "Number of accepted reservations on the ReservationRequestsPage is not as expected");

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

        assertEquals(reservationsPage.waitForCancelStatusElements(), initCancelledReservationCountOnPage+1, "Number of cancelled reservations on the ReservationRequestsPage is not as expected");
        assertEquals(reservationsPage.waitForAcceptStatusElements(), initAcceptedReservationCountOnPage-1, "Number of accepted reservations on the ReservationRequestsPage is not as expected");

        reservationsPage2.clickLogo();

//
        //Index
        IndexPage indexPage2 = new IndexPage(driver,true);
        assertTrue(indexPage2.isPageOpened());
        indexPage2.openMenuHost();
        indexPage2.logoutHost();

        System.out.println("Finished test: View and Cancel Reservation - Guest");

    }


    @Test
    @DisplayName("#02-Test: View And Approve Reservation - Host")
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

        int acceptedReservationCountOnPage = reservationRequestsPage.getNumberOfAcceptedReservations();
        int rejectedReservationCountOnPage = reservationRequestsPage.getNumberOfRejectedReservations();
        int initWaitingReservationCountOnPage = reservationRequestsPage.getNumberOfWaitingReservations();

        System.out.println(acceptedReservationCountOnPage +" "+ rejectedReservationCountOnPage + " " + initWaitingReservationCountOnPage);

        reservationRequestsPage.clickOnWaitingForApproval();
        assertNotEquals(0,initWaitingReservationCountOnPage);

        int overlappingReservations = reservationRequestsPage.getNumberOfOverlappingReservations();

        reservationRequestsPage.acceptReservation();

        System.out.println(overlappingReservations);


        assertTrue(reservationRequestsPage.isReservationSuccessfullyAccepted());
        reservationRequestsPage.clickConfirmButton();


        reservationRequestsPage.clickOnWaitingForApproval();
        int waitingReservationCountOnPage =reservationRequestsPage.getNumberReservations();
        System.out.println(acceptedReservationCountOnPage +" "+ rejectedReservationCountOnPage + " " + waitingReservationCountOnPage);

        //ovde treba overlap
        assertEquals(initWaitingReservationCountOnPage-overlappingReservations-1, waitingReservationCountOnPage, "Number of waiting reservations on the ReservationRequestsPage is not as expected");

        reservationRequestsPage.clickOnAccepted();
        assertEquals(acceptedReservationCountOnPage+1, reservationRequestsPage.getNumberOfAcceptedReservations(), "Number of accepted reservations on the ReservationRequestsPage is not as expected");

        reservationRequestsPage.clickOnRejected();
        //ovde treba overlap
        assertEquals(rejectedReservationCountOnPage+overlappingReservations, reservationRequestsPage.getNumberOfRejectedReservations(), "Number of rejected reservations on the ReservationRequestsPage is not as expected");

        reservationRequestsPage.clickLogo();

        //Index
        IndexPage indexPage1 = new IndexPage(driver,true);
        assertTrue(indexPage1.isPageOpened());
        indexPage1.openMenuHost();
        indexPage1.logoutHost();

        System.out.println("Finished test: View And Approve Reservation - Host");


    }

}
