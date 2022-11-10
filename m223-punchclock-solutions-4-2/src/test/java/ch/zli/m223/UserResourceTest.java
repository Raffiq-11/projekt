package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.ApplicationUser;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
public class UserResourceTest {

  @Test
  public void testIndexEndpoint() {
    given()
      .when().get("/users")
      .then()
       .statusCode(200)
       .body(is("[]"));
  }

  @Test
  public void testDeleteEndpoint() {
    var user = new ApplicationUser();
    user.setFirstname("firstname");
    user.setLastname("lastname");
    user.setEmail("firstname.lastname@gmail.com");
    user.setPassword("password");
    user.setActive(true);
    user.setIsAdmin(false);


    var createResponse = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/users");

    given()
      .when().delete("/users/" + createResponse.jsonPath().get("id"))
      .then()
        .statusCode(204);
  }

  @Test
  public void testUpdateEndpoint() {
    var user = new ApplicationUser();
    user.setFirstname("firstname");
    user.setLastname("lastname");
    user.setEmail("firstname.lastname@gmail.com");
    user.setPassword("password");
    user.setActive(true);
    user.setIsAdmin(false);

    var createResponse = given()
      .contentType(ContentType.JSON)
      .body(user)
      .when().post("/users");


    var updatedUser = new ApplicationUser();
    updatedUser.setFirstname("firstname");
    updatedUser.setLastname("lastname");
    updatedUser.setEmail("firstname.lastname@gmail.com");
    updatedUser.setPassword("password123");
    updatedUser.setActive(true);
    updatedUser.setIsAdmin(false);

    given()
      .contentType(ContentType.JSON)
      .body(updatedUser)
      .when().put("/users/" + createResponse.jsonPath().get("id"))
      .then()
        .statusCode(200)
        .body("password", is("password123"));
  }

}