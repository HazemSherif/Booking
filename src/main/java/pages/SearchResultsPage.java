package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class SearchResultsPage {
    WebDriver driver;
    String hotel;

    public SearchResultsPage(WebDriver driver) {
        // Initialize the page with the provided WebDriver instance
        this.driver = driver;
    }

    private final By loadMoreButton = By.xpath("//button//span[contains(text(),'Load more results')]");
    private final By seeAvailabilityButton = By.xpath("//div[contains(text(),'Tolip')]/ancestor::div[@data-testid='property-card']//a[@data-testid='availability-cta-btn']");


    public void SelectHotel(String hotel) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        boolean isHotelFound = false;
        while (!isHotelFound) {
            try {
                wait.until(d -> {
                    driver.findElement(By.xpath("//div[contains(text(),'" + hotel + "')]"));
                    System.out.println("Hotel found: " + hotel);
                    return true;
                });
                isHotelFound = true;
                break;
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");
                System.out.println("Hotel not found, scrolling down...");
            }
            if (driver.findElements(loadMoreButton).size() > 0) {
                wait.until(d -> {
                    driver.findElement(loadMoreButton)
                            .click();
                    System.out.println("Load more results button encountered...");
                    return true;
                });
            }
        }
        if(isHotelFound){
            wait.until(d -> {
                driver.findElement(seeAvailabilityButton).click();

                return true;
            });
            driver.switchTo().window(
                    new WebDriverWait(driver, Duration.ofSeconds(10))
                            .until(d -> {
                                Set<String> handles = d.getWindowHandles();
                                return handles.size() > 1
                                        ? handles.toArray()[handles.size() - 1].toString()
                                        : null;
                            })
            );
        }
    }
}
