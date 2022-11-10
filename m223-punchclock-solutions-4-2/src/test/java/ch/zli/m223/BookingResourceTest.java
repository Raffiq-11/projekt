package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Booking;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDate;

@QuarkusTest
public class BookingResourceTest {

  @Test
  public void testIndexEndpoint() {
    given()
      .when().get("/bookings")
      .then()
       .statusCode(200)
       .body(is("[]"));
  }

  @Test
  public void testDeleteEndpoint() {
    var booking = new Booking();
    booking.setDate(LocalDate.now());
    booking.setIsFullDay(false);
    booking.setActive(true);
    booking.setIsAccepted(true);


    var createResponse = given()
      .contentType(ContentType.JSON)
      .body(booking)
      .when().post("/bookings");

    given()
      .when().delete("/bookings/" + createResponse.jsonPath().get("id"))
      .then()
        .statusCode(204);
  }

  @Test
  public void testUpdateEndpoint() {
    var booking = new Booking();
    booking.setDate(LocalDate.now());
    booking.setIsFullDay(false);
    booking.setActive(true);
    booking.setIsAccepted(true);

    var createResponse = given()
      .contentType(ContentType.JSON)
      .body(booking)
      .when().post("/bookings");


    var updatedBooking = new Booking();
    updatedBooking.setDate(LocalDate.of(2004, 10, 16));
    updatedBooking.setIsFullDay(false);
    updatedBooking.setActive(true);
    updatedBooking.setIsAccepted(true);

    given()
      .contentType(ContentType.JSON)
      .body(updatedBooking)
      .when().put("/bookings/" + createResponse.jsonPath().get("id"))
      .then()
        .statusCode(200)
        .body("date", is("2004-10-16"));
  }

}