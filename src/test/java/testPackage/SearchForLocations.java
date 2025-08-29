package testPackage;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.DetailsPage;
import pages.ReservationPage;
import pages.SearchResultsPage;
import utilities.JsonUtils;

import java.io.IOException;

public class SearchForLocations extends TestCase{

    @DataProvider(name = "jsonData")
    public Object[][] getData() throws IOException {
        return JsonUtils.getJsonData("src/test/resources/HotelData.json");
    }

    @Test(dataProvider = "jsonData")
    @Description(".")
    public void SearchForHotel(
            String destination,String hotelName, String startDate, String endDate, String startDateAbbreviation, String endDateAbbreviation, String searchMonth)
            throws InterruptedException {

        driver.get("https://www.booking.com");
        DashboardPage dashboard = new DashboardPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        DetailsPage detailsPage = new DetailsPage(driver);

        dashboard.TypeDestinationArea(destination);
        dashboard.selectStayDates(startDate,endDate,searchMonth);
        dashboard.submitSearch();
        searchResultsPage.SelectHotel(hotelName);
        detailsPage.makeAReservation();
        Assert.assertTrue(detailsPage.checkInDate(startDateAbbreviation));
        Assert.assertTrue(detailsPage.checkOutDate(endDateAbbreviation));
        Assert.assertTrue(new ReservationPage(driver).checkHotelName(hotelName));
    }
}
