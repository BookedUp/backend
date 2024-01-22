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

public class SearchPage {
    private WebDriver driver;

    private final static String PAGE_URL = "http://localhost:4200";

    @FindBy(id = "main-search-box")
    private WebElement searchBox;

    @FindBy(id = "budget")
    private WebElement budgetSlider;

    @FindBy(id = "FREE_WIFI")
    private WebElement freeWifiCheckbox;

    @FindBy(id = "NON_SMOKING_ROOMS")
    private WebElement nonSmokingRoomsCheckbox;

    @FindBy(id = "100-500")
    private WebElement slider;

    @FindBy(id = "500-1000")
    private WebElement slider1;

    @FindBy(id = "propertyNameInput")
    private WebElement propertyNameInput;

    @FindBy(id = "villaType")
    private WebElement villaType;

    @FindBy(id = "resortType")
    private WebElement resortType;

    @FindBy(id = "NofilterCheckbox")
    private WebElement nofilterCheckbox;

    @FindBy(id = "unitPrice")
    private WebElement unitPriceElement;

    @FindBy(id = "visibility")
    private WebElement visibility;

    @FindBy(id = "fromDate")
    private WebElement fromDateInput;

    @FindBy(id = "toDate")
    private WebElement toDateInput;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
//        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(searchBox));
        return element.isDisplayed();
    }

    public void setBudgetSliderValue() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(slider)).isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(slider).perform();
        slider.click();
        wait.until(ExpectedConditions.visibilityOf(visibility)).isDisplayed();

    }

    public void setBudgetSliderValue1()  {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(slider1)).isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(slider1).perform();
        slider1.click();
        wait.until(ExpectedConditions.visibilityOf(visibility)).isDisplayed();

    }



    public void clickFreeWifiCheckbox()  {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(freeWifiCheckbox)).isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(freeWifiCheckbox).perform();
        freeWifiCheckbox.click();
        wait.until(ExpectedConditions.visibilityOf(visibility)).isDisplayed();
    }

    public void clickNonSmokingRoomsCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(nonSmokingRoomsCheckbox)).isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(nonSmokingRoomsCheckbox).perform();
        nonSmokingRoomsCheckbox.click();
        wait.until(ExpectedConditions.visibilityOf(visibility)).isDisplayed();

    }

    public void setSearchPropertyName(String propertyName) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='eg. Beach westpalm']")));
        propertyNameInput.clear();
        propertyNameInput.sendKeys(propertyName);
        wait.until(ExpectedConditions.visibilityOf(visibility)).isDisplayed();

    }
    public void clearSearchPropertyName() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='eg. Beach westpalm']")));
        propertyNameInput.clear();
        wait.until(ExpectedConditions.visibilityOf(visibility)).isDisplayed();

    }

    public void clickVillaType() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(villaType)).isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(villaType).perform();
        villaType.click();
        wait.until(ExpectedConditions.visibilityOf(visibility)).isDisplayed();

    }

    public void clickResortType() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(resortType)).isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(resortType).perform();
        resortType.click();
        wait.until(ExpectedConditions.visibilityOf(visibility)).isDisplayed();

    }

    public void clickNofilterCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(nofilterCheckbox)).isDisplayed();
        Actions actions = new Actions(driver);
        actions.moveToElement(nofilterCheckbox).perform();
        nofilterCheckbox.click();
        wait.until(ExpectedConditions.visibilityOf(visibility)).isDisplayed();

    }

    public boolean isUnitPriceDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(unitPriceElement));
        return element.isDisplayed();

    }

    public int getNumberOfSearchResults() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("frames-apartments")));

        WebElement resultsContainer = driver.findElement(By.id("frames-apartments"));

        return resultsContainer.findElements(By.id("resultAccommodation")).size();
    }


    public void inputFromDate(String formattedDate) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(fromDateInput)).clear();

        // Formatirajte datum u željeni format (npr. MM/dd/yyyy)
//        String formattedDate = "03/02/2024"; // Prilagodite ovu vrednost prema vašim potrebama

        // Izvršite JavaScript kako biste postavili vrednost input polja
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='" + formattedDate + "';", fromDateInput);
    }



    public void inputToDate(String formattedDate) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(toDateInput)).clear();

        // Formatirajte datum u željeni format (npr. MM/dd/yyyy)
//        String formattedDate = "06/02/2024"; // Prilagodite ovu vrednost prema vašim potrebama

        // Izvršite JavaScript kako biste postavili vrednost input polja
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='" + formattedDate + "';", toDateInput);
    }

    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.visibilityOf(visibility)).isDisplayed();

    }



}
