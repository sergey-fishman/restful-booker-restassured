package controllers;

import dto.DtoBooking;
import dto.Token;
import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import utils.BaseApi;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.PropertiesReader.*;

public class BookingController implements BaseApi {
    public Token token;

    @BeforeClass
    public void createToken() {
        User user = User.builder()
                .username(getProperty("base.properties", "username"))
                .password(getProperty("base.properties", "password"))
                .build();
        token = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + AUTH_URL)
                .thenReturn()
                .getBody().as(Token.class);
    }

    public Response ping() {
        return given()
                .when()
                .get(BASE_URL + PING_URL)
                .thenReturn();
    }

    public Response getBookingIds() {
        return given()
                .when()
                .get(BASE_URL + BOOKING_URL)
                .thenReturn();
    }

    public Response getBookingIds(String firstname, String lastname, String checkin, String checkout) {
        Map<String, Object> params = new LinkedHashMap<>();
        if (firstname != null) params.put("firstname", firstname);
        if (lastname != null) params.put("lastname", lastname);
        if (checkin != null) params.put("checkin", checkin);
        if (checkout != null) params.put("checkout", checkout);
        return given()
                .log().all()
                .queryParams(params)
                .get(BASE_URL + BOOKING_URL)
                .then()
                .log().body()
                .extract().response();
    }

    public Response getBooking(String id) {
        return given()
                .log().uri()
                .pathParam("id", id)
                .get(BASE_URL + BOOKING_URL + "/{id}")
                .then()
                .log().body()
                .extract().response();
    }

    public Response createBooking(DtoBooking booking) {
        return given()
                .log().body()
                .body(booking)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + BOOKING_URL)
                .then()
                .log().body()
                .extract().response();
    }

    public Response updateBookingBasicAuth(DtoBooking booking, String id) {
        return given()
                .log().all()
                .body(booking)
                .contentType(ContentType.JSON)
                .auth().preemptive().basic(getProperty("base.properties", "username"),
                        getProperty("base.properties", "password"))
                .pathParam("id", id)
                .when()
                .put(BASE_URL + BOOKING_URL + "/{id}")
                .then()
                .log().body()
                .extract().response();
    }

    public Response deleteBookingCookie(String id) {
        return given()
                .log().all()
                .header("Cookie", "token="+token.getToken())
                .pathParam("id", id)
                .when()
                .delete(BASE_URL + BOOKING_URL + "/{id}")
                .thenReturn();
    }
}
