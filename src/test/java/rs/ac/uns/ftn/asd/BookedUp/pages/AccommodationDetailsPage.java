package rs.ac.uns.ftn.asd.BookedUp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AccommodationDetailsPage{
    private WebDriver driver;

    private final static String PAGE_URL = "http://localhost:4200";

    @FindBy(id = "apartmentName")
    private WebElement apartmentName;

    @FindBy(id = "profile-picture-host")
    private WebElement profilePicture;
    @FindBy(id = "logo")
    private WebElement logo;

    @FindBy(id = "hostDropdownContainer")
    private WebElement hostDropdownContainer;

    @FindBy(id = "reservationsNavItem")
    private WebElement reservationsNavItem;

    @FindBy(id = "accommodationsNavItem")
    private WebElement accommodationsNavItem;
    @FindBy(id = "signOut")
    private WebElement signOut;

    @FindBy(id = "locationTxt")
    private WebElement locationInput;

    @FindBy(id = "fromDate")
    private WebElement fromDateInput;

    @FindBy(id = "toDate")
    private WebElement toDateInput;

    @FindBy(id = "guestNumberTxt")
    private WebElement guestNumberInput;

    @FindBy(id = "searchButton")
    private WebElement searchButton;



    // Add more @FindBy annotations for other elements as needed

    public AccommodationDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(apartmentName));
        return element.isDisplayed();
    }

    public void openMenuHost() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(profilePicture)).isDisplayed();
        actions.moveToElement(profilePicture).perform();
        profilePicture.click();

    }
    public void selectAccommodationsHost() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement hostDropdown = wait.until(ExpectedConditions.visibilityOf(hostDropdownContainer));
        Actions actions = new Actions(driver);
        actions.moveToElement(hostDropdown).perform();
        accommodationsNavItem.click();
    }


    public void selectReservationsHost() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement hostDropdown = wait.until(ExpectedConditions.visibilityOf(hostDropdownContainer));
        Actions actions = new Actions(driver);
        actions.moveToElement(hostDropdown).perform();
        reservationsNavItem.click();
    }




    public void logoutHost() {
        WebDriverWait wait = new WebDriverWait(driver,15);
        WebElement hostDropdown = wait.until(ExpectedConditions.visibilityOf(hostDropdownContainer));
        Actions actions = new Actions(driver);
        actions.moveToElement(hostDropdown).perform();
        signOut.click();
    }

    public void inputLocationTxt(String location) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(locationInput)).clear();
        locationInput.sendKeys(location);
    }

    public void inputFromDate(String formattedDate) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(fromDateInput)).clear();

        // Formatirajte datum u željeni format (npr. MM/dd/yyyy)
//        String formattedDate = "01/30/2024"; // Prilagodite ovu vrednost prema vašim potrebama

        // Izvršite JavaScript kako biste postavili vrednost input polja
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='" + formattedDate + "';", fromDateInput);
    }



    public void inputToDate(String formattedDate) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(toDateInput)).clear();

        // Formatirajte datum u željeni format (npr. MM/dd/yyyy)
//        String formattedDate = "02/02/2024"; // Prilagodite ovu vrednost prema vašim potrebama

        // Izvršite JavaScript kako biste postavili vrednost input polja
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='" + formattedDate + "';", toDateInput);
    }

    public void inputGuest(String guestNumber) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(guestNumberInput)).clear();
        guestNumberInput.sendKeys(guestNumber);
    }

    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public boolean isDateAvailable(String date) throws InterruptedException {
        List<WebElement> dateElements = driver.findElements(By.cssSelector(".calendar .date"));
        List<WebElement> unavailableDateElements = driver.findElements(By.cssSelector(".date.already-picked"));

        for (WebElement dateElement : dateElements) {
            String dayText = dateElement.findElement(By.xpath(".//div[1]")).getText();

            if (date.equals(dayText) && !unavailableDateElements.contains(dateElement)) {
                return true;
            }
        }
        return false;

    }

    public boolean isPriceCorrectForDate(String date, String expectedPrice) {
        List<WebElement> alreadyPickedDateElements = driver.findElements(By.cssSelector(".date.already-picked"));

        for (WebElement dateElement : alreadyPickedDateElements) {
            String dayText = dateElement.findElement(By.xpath(".//div[1]")).getText();
            String actualPriceText = dateElement.findElement(By.cssSelector(".price-text")).getText().replace("$", "");

            if (date.equals(dayText) && expectedPrice.equals(actualPriceText)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", dateElement);

                return true; // Cena se podudara za određeni datum
            }
        }

        return false; // Cena nije pronađena ili se ne podudara za određeni datum
    }

    public void clickLogo() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        WebElement visibleLogo = wait.until(ExpectedConditions.visibilityOf(logo));
        actions.moveToElement(logo).perform();
        visibleLogo.click();
    }




}
