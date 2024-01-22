package rs.ac.uns.ftn.asd.BookedUp.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ReservationsPage {
    private WebDriver driver;

    @FindBy(id = "sort-bar")
    private WebElement sortBar;

    @FindBy(id = "cancelledReservations")
    private WebElement cancelledReservationsButton;

    @FindBy(id = "acceptedReservations")
    private WebElement acceptedReservationsButton;

    @FindBy(id = "accept-button")
    private WebElement acceptButton;

    @FindBy(id = "swal2-title")
    private WebElement successPopup;

    @FindBy(css = ".swal2-confirm.swal2-styled")
    private WebElement confirmButton;

    @FindBy(css = ".acc-frame")
    private WebElement accFrame;
    @FindBy(id = "logo")
    private WebElement logo;

    public ReservationsPage(WebDriver driver) {
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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("main-content")));

        WebElement mainContent = driver.findElement(By.id("main-content"));
        List<WebElement> reservationElements = mainContent.findElements(By.cssSelector(".acc-frame"));

        return reservationElements.size();
    }


    public void clickOnCancelled() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement cancelButton = wait.until(ExpectedConditions.visibilityOf(cancelledReservationsButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(cancelButton).perform();
        cancelButton.click();
        wait.until(ExpectedConditions.visibilityOf(accFrame)).isDisplayed();


    }

    public void clickOnCancelled1() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement cancelButton = wait.until(ExpectedConditions.visibilityOf(cancelledReservationsButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(cancelButton).perform();
        cancelButton.click();
        wait.until(ExpectedConditions.visibilityOf(accFrame)).isDisplayed();


    }

    public void clickOnAccepted() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement acceptButton = wait.until(ExpectedConditions.visibilityOf(acceptedReservationsButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(acceptButton).perform();
        acceptButton.click();
        wait.until(ExpectedConditions.visibilityOf(accFrame)).isDisplayed();

    }

    public void clickLogo() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        WebElement visibleLogo = wait.until(ExpectedConditions.visibilityOf(logo));
        actions.moveToElement(logo).perform();
        visibleLogo.click();
    }
    public void clickViewDetailsButton(String reservationId) {
        String selector = String.format(".acc-frame[routerLink='/reservation-details/%s'] .view-details-button", reservationId);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        WebElement viewDetailsButton = driver.findElement(By.cssSelector(selector));
        wait.until(ExpectedConditions.visibilityOf(viewDetailsButton)).isDisplayed();
        actions.moveToElement(viewDetailsButton).perform();
        viewDetailsButton.click();
    }

    public void clickViewDetailsButton(int accFrameIndex) {
        List<WebElement> accFrames = driver.findElements(By.cssSelector(".acc-frame"));
        if (accFrameIndex >= 0 && accFrameIndex < accFrames.size()) {
            WebElement accFrame = accFrames.get(accFrameIndex);
            WebElement viewDetailsButton = accFrame.findElement(By.cssSelector(".view-details-button"));
            viewDetailsButton.click();
        } else {
            throw new NoSuchElementException("Invalid acc frame index: " + accFrameIndex);
        }
    }

    // Metoda za proveru vidljivosti "Cancel" dugmeta u detaljima
//    public boolean isCancelVisible() {
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cancel-button"))); // Prilagodite id ako je drugačiji
//            return true;
//        } catch (NoSuchElementException | TimeoutException e) {
//            return false;
//        }
//    }





}

