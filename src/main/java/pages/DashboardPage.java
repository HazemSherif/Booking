package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    WebDriver driver;
    String destination;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));


    public DashboardPage(WebDriver driver) {
        // Initialize the page with the provided WebDriver instance
        this.driver = driver;
    }

    private final By destinationSearchField = By.xpath("//input[@id= ':rh:']");
    //private final By autoCompleteOption = By.xpath("//li[@id = 'autocomplete-result-0']//div[contains(text(),'" + destination + "')]");
    private final By startDatePicker = By.xpath("//span[@data-testid='date-display-field-start']");
    private final By nextMonthButton = By.xpath("//button[@aria-label='Next month']");
    private final By startDateOption = By.xpath("//span[@aria-label='We 1 October 2025']");
    private final By endDateOption = By.xpath("//span[@aria-label='Tu 14 October 2025']");
    private final By submitButton = By.xpath("//button[@type='submit']");

    public void TypeDestinationArea(String destination) {
        wait.until(d -> {
            driver.findElement(destinationSearchField)
                    .sendKeys(destination);
            return true;
        });
        // waits until the autocomplete option appears
        wait.until(d -> {
            driver.findElement(By.xpath("//li[@id = 'autocomplete-result-0']//div[contains(text(),'" + destination + "')]"));
            return true;
        });



    }
    public void selectStayDates(String startDate, String endDate, String searchMonth) {
        wait.until(d -> {
            driver.findElement(startDatePicker)
                    .click();
            return true;
        });

        while(driver.findElements(By.xpath("//div[@id='calendar-searchboxdatepicker']//h3[contains(text(), '" + searchMonth +"')]")).isEmpty())
        {
            wait.until(d -> {
                driver.findElement(nextMonthButton)
                        .click();
                return true;
            });
            System.out.println("Waiting for the month to change...");
        }
        wait.until(d -> {
            driver.findElement(startDateOption)
                    .click();
            return true;
        });
        wait.until(d -> {
            driver.findElement(endDateOption)
                    .click();
            return true;
        });

    }
    public void submitSearch() {
        wait.until(d -> {
            driver.findElement(submitButton)
                    .click();
            return true;
        });
    }
}
