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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservationRequestsPage {
    private WebDriver driver;

    private final static String PAGE_URL = "http://localhost:4200/reservation-requests";

    @FindBy(id = "sort-bar")
    private WebElement sortBar;

    @FindBy(className = "waiting-reservation")
    private WebElement waitingForApprovalButton;

    @FindBy(id = "accepted")
    private WebElement acceptedButton;
    @FindBy(id = "rejected")
    private WebElement rejectedButton;

    @FindBy(id = "accept-button")
    private WebElement acceptButton;

    @FindBy(id = "swal2-title")
    private WebElement successPopup;

    @FindBy(css = ".swal2-confirm.swal2-styled")
    private WebElement confirmButton;

    @FindBy(id = "logo")
    private WebElement logo;

    public ReservationRequestsPage(WebDriver driver) {
        this.driver = driver;
//        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(sortBar));
        return element.isDisplayed();
    }

    public int getNumberOfReservations() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        List<WebElement> reservationElements = driver.findElements(By.cssSelector(".acc-frame"));
        return reservationElements.size();
    }

    public int getNumberOfAcceptedReservations() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".acc-frame.accepted-status")));
        List<WebElement> acceptedReservationElements = driver.findElements(By.cssSelector(".acc-frame.accepted-status"));
        return acceptedReservationElements.size();
    }


    public int getNumberOfRejectedReservations() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".acc-frame.rejected-status")));
        List<WebElement> cancelledReservationElements = driver.findElements(By.cssSelector(".acc-frame.rejected-status"));
        return cancelledReservationElements.size();
    }

    public int getNumberOfWaitingReservations() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".acc-frame.created-status")));
        List<WebElement> waitingReservationElements = driver.findElements(By.cssSelector(".acc-frame.created-status"));
        return waitingReservationElements.size();
    }


    public void clickOnWaitingForApproval() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(waitingForApprovalButton)).isDisplayed();
        actions.moveToElement(waitingForApprovalButton).perform();
        waitingForApprovalButton.click();
    }

    public void acceptReservation() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(acceptButton)).isDisplayed();
        actions.moveToElement(acceptButton).perform();
        acceptButton.click();
    }

    public boolean isReservationSuccessfullyAccepted() {
        boolean isPoppupShowed = (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.textToBePresentInElement(successPopup, "Reservation Approved!"));

        return isPoppupShowed;
    }

    public void clickConfirmButton() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(successPopup)).isDisplayed();
        actions.moveToElement(confirmButton).perform();
        confirmButton.click();
    }

    public void clickLogo() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        WebElement visibleLogo = wait.until(ExpectedConditions.visibilityOf(logo));
        actions.moveToElement(logo).perform();
        visibleLogo.click();
    }

    public void clickOnAccepted() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(acceptedButton)).isDisplayed();
        actions.moveToElement(acceptedButton).perform();
        acceptedButton.click();
    }

    public void clickOnRejected() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOf(rejectedButton)).isDisplayed();
        actions.moveToElement(rejectedButton).perform();
        rejectedButton.click();
    }

    public int getNumberReservations() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Sačekaj da se vrednost elementa promeni na očekivanu vrednost
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("numberOfReservations")));


        // Izvršavanje JavaScript koda za dohvatanje vrednosti broja rezervacija
        String script = "return document.getElementById('numberOfReservations').textContent.trim();";
        String numberOfReservationsText = (String) js.executeScript(script);

        // Pretvaranje tekstualne vrednosti u integer
        return Integer.parseInt(numberOfReservationsText);
    }

    public int getNumberOfOverlappingReservations() {
        List<WebElement> reservationElements = driver.findElements(By.cssSelector(".acc-frame"));

        int overlappingCount = 0;

        WebElement initElem = reservationElements.get(0);

        WebElement imageElement = driver.findElement(By.id("image"));

        WebElement initCheckInElement = initElem.findElement(By.id("checkIn"));
        WebElement initCheckOutElement = initElem.findElement(By.id("checkOut"));


        String initCheckInText = initCheckInElement.getText().replace("Check In: ", "");
        String initCheckOutText = initCheckOutElement.getText().replace("Check Out: ", "");

        LocalDate initCheckInDate = parseDate(initCheckInText);
        LocalDate initCheckOutDate = parseDate(initCheckOutText);

        for (int i = 1; i < reservationElements.size(); i++) {


            WebElement reservationElement = reservationElements.get(i);

            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOf(reservationElement));

            WebElement checkInElement = reservationElement.findElement(By.id("checkIn"));
            WebElement checkOutElement = reservationElement.findElement(By.id("checkOut"));


            String checkInText = checkInElement.getText().replace("Check In: ", "");
            String checkOutText = checkOutElement.getText().replace("Check Out: ", "");

            LocalDate checkInDate = parseDate(checkInText);
            LocalDate checkOutDate = parseDate(checkOutText);


            // Provera preklapanja sa drugim rezervacijama
            if (hasOverlappingReservations(initCheckInDate, initCheckOutDate, checkInDate, checkOutDate)) {
                overlappingCount++;
            }
        }

        return overlappingCount;
    }

    private LocalDate parseDate(String dateText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, y");
        return LocalDate.parse(dateText, formatter);
    }

    private boolean hasOverlappingReservations(LocalDate initCheckIn, LocalDate initCheckOut, LocalDate checkIn, LocalDate checkOut) {
//        for (WebElement otherReservation : reservationElements) {
//            if (otherReservation.equals(current)) {
//                continue; // Preskoči trenutnu rezervaciju
//            }
//
//            // Dohvatanje elemenata sa ID-jevima checkIn i checkOut unutar druge rezervacije
//            WebElement otherCheckInElement = otherReservation.findElement(By.id("checkIn"));
//            WebElement otherCheckOutElement = otherReservation.findElement(By.id("checkOut"));
//
//// Dobijanje teksta i uklanjanje "Check In: " i "Check Out: "
//            String otherCheckInText = otherCheckInElement.getText().replace("Check In: ", "");
//            String otherCheckOutText = otherCheckOutElement.getText().replace("Check Out: ", "");
//
//// Parsiranje datuma iz teksta
//            LocalDate otherCheckInDate = parseDate(otherCheckInText);
//            LocalDate otherCheckOutDate = parseDate(otherCheckOutText);


            // Provera preklapanja datuma
        if (checkIn.isAfter(initCheckIn) && checkIn.isBefore(initCheckOut)) {
            // checkIn se nalazi između initCheckIn i initCheckOut
            return true;
        } else if (checkOut.isAfter(initCheckIn) && checkOut.isBefore(initCheckOut)){
            return true;
        }else if (checkIn.isBefore(initCheckIn) && checkOut.isAfter(initCheckOut)) {
            return true;
        }else if (checkIn.isEqual(initCheckIn) || checkIn.isEqual(initCheckOut) || checkOut.isEqual(initCheckIn) || checkOut.isEqual(initCheckOut) ) {
            return true;
        }else{
            return false;
        }

    }
}
