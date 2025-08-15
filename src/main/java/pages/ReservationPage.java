package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReservationPage {
    WebDriver driver;

    public ReservationPage(WebDriver driver) {
        this.driver = driver;
    }

    By hotelNameHighlight = By.xpath("//div[@data-capla-component-boundary='b-checkout-bp-accommodation/PropertyDetails/v2/default-highlights']//h1");

    public Boolean checkHotelName(String HotelName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(d -> {
            // name field has no robust element locator using most robust locator
            driver.findElement(hotelNameHighlight);
            return true;
        });
        return driver.findElement(hotelNameHighlight)
                .getText()
                .equals(HotelName);
    }
}
