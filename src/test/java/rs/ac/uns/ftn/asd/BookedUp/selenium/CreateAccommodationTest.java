package rs.ac.uns.ftn.asd.BookedUp.selenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import rs.ac.uns.ftn.asd.BookedUp.pages.*;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationService;
import rs.ac.uns.ftn.asd.BookedUp.service.ReservationService;
import rs.ac.uns.ftn.asd.BookedUp.service.UserService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateAccommodationTest extends TestBase {


    @Test
    @DisplayName("#01-Test: Create Accommodation -  Define Availability And Price ")
    public void testSearchAndFilterAccommodation() throws InterruptedException {

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
        indexPage.selectAccommodationsHost();

        //Accommodations
        AccommodationsPage accommodationPage = new AccommodationsPage(driver);
        assertTrue(accommodationPage.isPageOpened());
        accommodationPage.clickAddAccommodationButton();

        //CreateAccommodation
        CreateAccommodationPage createAccommodationPage = new CreateAccommodationPage(driver);
        assertTrue(createAccommodationPage.isPageOpened());

        String price = "200";

        createAccommodationPage.fillAccommodationDetails("Hotel Moskva", "Trg Duska Trifunovica 4", "Crvenka", "Srbija", "25220", price, "Opis ovog hotela", "1", "2", "3");

        createAccommodationPage.addAvailability(25, 30);
        createAccommodationPage.deleteAvailability(27,29);

        createAccommodationPage.applyCustomPrice("5",25,30);
        createAccommodationPage.waitForAndClickOkButton();
        createAccommodationPage.clickAddNewAccommodationButton();
        createAccommodationPage.clickOkButton();

        //Index
        IndexPage indexPage2 = new IndexPage(driver, true);
        indexPage2.isPageOpened();
        indexPage2.openMenuHost();
        indexPage2.selectAccommodationsHost();

        //Accommodations
        AccommodationsPage accommodationsPage2 = new AccommodationsPage(driver);
        assertTrue(accommodationsPage2.isPageOpened());
        accommodationsPage2.clickOnWaitingForApproval();
        accommodationsPage2.clickLastViewDetailsButton();

        //AccommodationDetails
        AccommodationDetailsPage accommodationDetailsPage = new AccommodationDetailsPage(driver);
        assertTrue(accommodationDetailsPage.isPageOpened());

        assertTrue(accommodationDetailsPage.isDateAvailable("25"));
        assertFalse(accommodationDetailsPage.isDateAvailable("28"));

        assertTrue(accommodationDetailsPage.isPriceCorrectForDate("17", price));
        assertFalse(accommodationDetailsPage.isPriceCorrectForDate("25", price));

        accommodationDetailsPage.clickLogo();

        //Index
        IndexPage indexPage1 = new IndexPage(driver,true);
        assertTrue(indexPage1.isPageOpened());
        indexPage1.openMenuHost();
        indexPage1.logoutHost();

        System.out.println("Finished test:  Create Accommodation -  Define Availability And Price ");


    }
}

