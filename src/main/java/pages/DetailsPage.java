package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class DetailsPage {

    WebDriver driver;
    boolean isRoomFound = false;

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    //By locator of the first room available
    By dropDown = By.xpath("//a[@class='hprt-roomtype-link']/ancestor::tr//select");
    By reserveButton = By.xpath("//span[contains(text(),\"I'll\")]/ancestor::button");
    By startDatePicker = By.xpath("//button[@data-testid='date-display-field-start']/span");

    public void makeAReservation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        System.out.println("Making a reservation...");
        while (!isRoomFound) {
            try {
                Select select = new Select(driver.findElement(dropDown));
                select.selectByIndex(1);
                wait.until(d -> {
                    driver.findElement(reserveButton).click();
                    return true;
                });
                isRoomFound = true;
                System.out.println("Room selected and reservation made successfully.");
                break;
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");
                System.out.println("No Room found, scrolling down...");
            }
        }
    }

    public Boolean checkInDate(String checkInDate)
    {
        return Objects.requireNonNull(driver.getPageSource()).contains(checkInDate);
    }
    public Boolean checkOutDate(String checkOutDate)
    {
        return Objects.requireNonNull(driver.getPageSource()).contains(checkOutDate);
    }
}
