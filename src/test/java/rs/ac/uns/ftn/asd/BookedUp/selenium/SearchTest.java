package rs.ac.uns.ftn.asd.BookedUp.selenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.ReservationStatus;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.pages.*;
import rs.ac.uns.ftn.asd.BookedUp.service.AccommodationService;
import rs.ac.uns.ftn.asd.BookedUp.service.ReservationService;
import rs.ac.uns.ftn.asd.BookedUp.service.UserService;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTest extends TestBase {

    @Test
    @DisplayName("#01-Test: Search And Filter Accommodations")
    public void testSearchAndFilterAccommodation() throws InterruptedException {

        //Index
        IndexPage indexPage = new IndexPage(driver, false);
        assertTrue(indexPage.isPageOpened());

        indexPage.inputLocationTxt("Italy");
        indexPage.inputFromDate("01/30/2024");
        indexPage.inputToDate("02/02/2024");
        indexPage.inputGuest("2");

        indexPage.clickSearchButton();

        //Search
        SearchPage searchPage = new SearchPage(driver);
        assertTrue(searchPage.isPageOpened());
        assertEquals(3, searchPage.getNumberOfSearchResults());

        searchPage.setBudgetSliderValue();
        assertEquals(2, searchPage.getNumberOfSearchResults());

        searchPage.setBudgetSliderValue1();
        assertEquals(1, searchPage.getNumberOfSearchResults());

        searchPage.setBudgetSliderValue();
        assertEquals(2, searchPage.getNumberOfSearchResults());

        searchPage.clickFreeWifiCheckbox();
        assertEquals(1, searchPage.getNumberOfSearchResults());

        searchPage.clickNonSmokingRoomsCheckbox();
        assertEquals(0, searchPage.getNumberOfSearchResults()); //0

        searchPage.clickFreeWifiCheckbox();
        assertEquals(1, searchPage.getNumberOfSearchResults());

        searchPage.clickNonSmokingRoomsCheckbox();
        assertEquals(2, searchPage.getNumberOfSearchResults());

        searchPage.setSearchPropertyName("Beachfront");
        assertEquals(1, searchPage.getNumberOfSearchResults());

        searchPage.clickResortType();
        assertEquals(0, searchPage.getNumberOfSearchResults());

        searchPage.clickVillaType();
        assertEquals(1, searchPage.getNumberOfSearchResults());

        searchPage.clearSearchPropertyName();

        searchPage.clickNofilterCheckbox();
        assertEquals(7, searchPage.getNumberOfSearchResults());

        assertTrue(searchPage.isUnitPriceDisplayed());

        searchPage.inputFromDate("03/02/2024");
        searchPage.inputToDate("06/02/2024");
        searchPage.clickSearchButton();
        assertEquals(5, searchPage.getNumberOfSearchResults());


        System.out.println("Finished test: Search And Filter Accommodation");


    }
}

