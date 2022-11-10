package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Status;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class StatusResourceTest {

  @Test
  public void testIndexEndpoint() {
    given()
      .when().get("/status")
      .then()
       .statusCode(200)
       .body(is("[]"));
  }

  @Test
  public void testDeleteEndpoint() {
    var status = new Status();
    status.setIsConfirmed(false);

    var createResponse = given()
      .contentType(ContentType.JSON)
      .body(status)
      .when().post("/status");

    given()
      .when().delete("/status/" + createResponse.jsonPath().get("id"))
      .then()
        .statusCode(204);
  }

  @Test
  public void testUpdateEndpoint() {
    var status = new Status();
    status.setIsConfirmed(false);
 

    var createResponse = given()
      .contentType(ContentType.JSON)
      .body(status)
      .when().post("/status");


    var updatedStatus = new Status();
    updatedStatus.setIsConfirmed(true);

    given()
      .contentType(ContentType.JSON)
      .body(updatedStatus)
      .when().put("/status/" + createResponse.jsonPath().get("id"))
      .then()
        .statusCode(200)
        .body("isConfirmed", is(true));
  }

}