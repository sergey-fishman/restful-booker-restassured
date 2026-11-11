package api;

import controllers.BookingController;
import dto.DtoBooking;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BookingFactory;

public class BookingTests extends BookingController {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void healthCheck() {
        Response response = ping();
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test
    public void getBookingIdsPositiveTest() {
        Response response = getBookingIds();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getBookingIdsQueryParamsPositiveTest() {
        Response response = getBookingIds(null, null,
                "2022-01-01", "2026-11-10");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getBookingByPathPositiveTest() {
        Response response = getBooking("1");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void createBookingPositiveTest() {
        DtoBooking booking = BookingFactory.positiveBooking();
        Response response = createBooking(booking);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void updateBookingBaseAuthPositiveTest() {
        DtoBooking booking = BookingFactory.positiveBooking();
        Response response = updateBookingBasicAuth(booking, "3941");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void deleteBookingCookiePositiveTest() {
        Response response = deleteBookingCookie("3985");
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
