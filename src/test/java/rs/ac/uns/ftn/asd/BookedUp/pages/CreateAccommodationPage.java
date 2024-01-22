package rs.ac.uns.ftn.asd.BookedUp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CreateAccommodationPage {
    private WebDriver driver;

    private final static String PAGE_URL = "http://localhost:4200";

    @FindBy(id = "acc-details")
    private WebElement sortBar;


    @FindBy(id = "name-input")
    private WebElement nameInput;

    @FindBy(id = "streetInput")
    private WebElement streetInput;

    @FindBy(id = "cityInput")
    private WebElement cityInput;

    @FindBy(id = "countryInput")
    private WebElement countryInput;

    @FindBy(id = "postalInput")
    private WebElement postalInput;

    @FindBy(id = "priceInput")
    private WebElement priceInput;

    @FindBy(id = "description-input")
    private WebElement descriptionInput;

    @FindBy(id = "perNight")
    private WebElement perNightCheckbox;

    @FindBy(id = "perGuest")
    private WebElement perGuestCheckbox;

    @FindBy(id = "facilities")
    private WebElement facilitiesCheckboxList;

    @FindBy(id = "accommodationType")
    private WebElement accommodationTypeCheckboxList;

    @FindBy(id = "minGuestInput")
    private WebElement minGuestInput;

    @FindBy(id = "maxGuestInput")
    private WebElement maxGuestInput;

    @FindBy(id = "cancellationInput")
    private WebElement cancellationInput;

    // Add more @FindBy annotations for other elements as needed

    public CreateAccommodationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(sortBar));
        return element.isDisplayed();
    }

    public void fillAccommodationDetails(String name, String street, String city, String country, String postal,
                                         String price, String description,
                                         String minGuest, String maxGuest, String cancellation) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        scrollIntoView(js, nameInput);
        wait.until(ExpectedConditions.visibilityOf(nameInput)).clear();
        nameInput.sendKeys(name);

        scrollIntoView(js, streetInput);
        wait.until(ExpectedConditions.visibilityOf(streetInput)).clear();
        streetInput.sendKeys(street);

        scrollIntoView(js, cityInput);
        wait.until(ExpectedConditions.visibilityOf(cityInput)).clear();
        cityInput.sendKeys(city);

        scrollIntoView(js, countryInput);
        wait.until(ExpectedConditions.visibilityOf(countryInput)).clear();
        countryInput.sendKeys(country);

        scrollIntoView(js, postalInput);
        wait.until(ExpectedConditions.visibilityOf(postalInput)).clear();
        postalInput.sendKeys(postal);

        scrollIntoView(js, priceInput);
        wait.until(ExpectedConditions.visibilityOf(priceInput)).clear();
        priceInput.sendKeys(price);

        scrollIntoView(js, descriptionInput);
        wait.until(ExpectedConditions.visibilityOf(descriptionInput)).clear();
        descriptionInput.sendKeys(description);

        WebElement accommodationTypeContainer = driver.findElement(By.id("accommodationType"));
        scrollIntoView(js, accommodationTypeContainer);
        List<WebElement> checkboxes = accommodationTypeContainer.findElements(By.cssSelector("input[type='checkbox']"));
        checkboxes.get(0).click();

        scrollIntoView(js, minGuestInput);
        wait.until(ExpectedConditions.visibilityOf(minGuestInput)).clear();
        minGuestInput.sendKeys(minGuest);

        scrollIntoView(js, maxGuestInput);
        wait.until(ExpectedConditions.visibilityOf(maxGuestInput)).clear();
        maxGuestInput.sendKeys(maxGuest);

        scrollIntoView(js, cancellationInput);
        wait.until(ExpectedConditions.visibilityOf(cancellationInput)).clear();
        cancellationInput.sendKeys(cancellation);
    }

    public void addAvailability(int... days) {
        List<WebElement> dateElements = driver.findElements(By.cssSelector(".date"));

        for (int day : days) {
            for (WebElement dateElement : dateElements) {
                String dayText = dateElement.findElement(By.xpath(".//div[1]")).getText();
                if (String.valueOf(day).equals(dayText)) {
                    dateElement.click();
                    break;
                }
            }
        }

        // Click on the "Add" button
        driver.findElement(By.id("addAv")).click();
    }

    public void deleteAvailability(int... days) {
        List<WebElement> dateElements = driver.findElements(By.cssSelector(".date"));

        for (int day : days) {
            for (WebElement dateElement : dateElements) {
                String dayText = dateElement.findElement(By.xpath(".//div[1]")).getText();
                if (String.valueOf(day).equals(dayText)) {
                    dateElement.click();
                    break;
                }
            }
        }

        // Click on the "Add" button
        driver.findElement(By.id("deleteAv")).click();
    }


    public void applyCustomPrice(int... selectedDays) {
        // Select specified days in the calendar
        List<WebElement> dateElements = driver.findElements(By.cssSelector(".date"));

        for (int day : selectedDays) {
            for (WebElement dateElement : dateElements) {
                String dayText = dateElement.findElement(By.xpath(".//div[1]")).getText();
                if (String.valueOf(day).equals(dayText)) {
                    dateElement.click();
                    break;
                }
            }
        }
        // Set custom price
        WebElement customPriceInput = driver.findElement(By.id("customPriceInput"));
        customPriceInput.clear();
        customPriceInput.sendKeys("5");

        // Click on the "Apply Custom Price" button
        driver.findElement(By.id("applyPrice")).click();
    }

    public void waitForAndClickOkButton() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-confirm")));
        okButton.click();
    }

    public void clickOkButton() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-confirm")));
        okButton.click();
    }


    public void clickAddNewAccommodationButton() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addNew")));
        addButton.click();
    }

    private void scrollIntoView(JavascriptExecutor js, WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center', behavior: 'smooth'});", element);
    }
}
