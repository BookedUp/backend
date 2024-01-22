package rs.ac.uns.ftn.asd.BookedUp.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReservationDetailsPage {
    private WebDriver driver;

    private final static String PAGE_URL = "http://localhost:4200";

    @FindBy(id = "visibility")
    private WebElement visibility;

    @FindBy(id = "cancel")
    private WebElement cancel;

    @FindBy(css = "button.swal2-confirm.swal2-styled")
    private WebElement okButton;

    @FindBy(id = "logo")
    private WebElement logo;

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

    public ReservationDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(visibility));
        return element.isDisplayed();
    }

    public boolean isCancelVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOf(cancel));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickLogo() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        WebElement visibleLogo = wait.until(ExpectedConditions.visibilityOf(logo));
        actions.moveToElement(logo).perform();
        visibleLogo.click();
    }

    public void clickCancelButton() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        WebElement visibleLogo = wait.until(ExpectedConditions.visibilityOf(cancel));
        actions.moveToElement(cancel).perform();
        visibleLogo.click();
    }

    public void clickOk() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        Actions actions = new Actions(driver);
        WebElement visibleLogo = wait.until(ExpectedConditions.visibilityOf(okButton));
        actions.moveToElement(okButton).perform();
        visibleLogo.click();

    }




    public void inputLocationTxt(String location) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(locationInput)).clear();
        locationInput.sendKeys(location);
    }

    public void inputFromDate() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(fromDateInput)).clear();

        // Formatirajte datum u željeni format (npr. MM/dd/yyyy)
        String formattedDate = "01/30/2024"; // Prilagodite ovu vrednost prema vašim potrebama

        // Izvršite JavaScript kako biste postavili vrednost input polja
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='" + formattedDate + "';", fromDateInput);
    }



    public void inputToDate() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(toDateInput)).clear();

        // Formatirajte datum u željeni format (npr. MM/dd/yyyy)
        String formattedDate = "02/02/2024"; // Prilagodite ovu vrednost prema vašim potrebama

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


}
