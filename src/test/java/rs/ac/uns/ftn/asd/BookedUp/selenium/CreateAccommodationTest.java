package rs.ac.uns.ftn.asd.BookedUp.selenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import rs.ac.uns.ftn.asd.BookedUp.pages.AccommodationsPage;
import rs.ac.uns.ftn.asd.BookedUp.pages.CreateAccommodationPage;
import rs.ac.uns.ftn.asd.BookedUp.pages.IndexPage;
import rs.ac.uns.ftn.asd.BookedUp.pages.LoginPage;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationService;
import rs.ac.uns.ftn.asd.BookedUp.service.ReservationService;
import rs.ac.uns.ftn.asd.BookedUp.service.UserService;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateAccommodationTest extends TestBase {


    @Test
    @DisplayName("#01-Test: Update Availability And Price Accommodations")
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

        createAccommodationPage.fillAccommodationDetails("proradi", "molim", "te", "proradi", "21000", "200", "molim teeeeeee", "1", "2", "3");

        createAccommodationPage.addAvailability(25, 30);
        createAccommodationPage.deleteAvailability(27,29);

        createAccommodationPage.applyCustomPrice(25,30);
        createAccommodationPage.waitForAndClickOkButton();
        createAccommodationPage.clickAddNewAccommodationButton();
        createAccommodationPage.clickOkButton();

        IndexPage indexPage2 = new IndexPage(driver, true);
        indexPage2.isPageOpened();


        System.out.println("Finished test: Update Availability And Price Accommodations");


    }
}

