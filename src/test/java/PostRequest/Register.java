package PostRequest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Register {

    @Test
    public void RegisterPostRequest() {

        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api/register";

        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("id", not(empty()))  // Assert that id is not empty
                .body("token", not(empty()))  // Assert that token is not empty
               .body("id", equalTo(4), "token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();

        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Test
    public void UnsuccessfulRegisterPostRequest() {

        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api/register";

        String requestBody = "{ \"email\": \"sydney@fife\" }";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(400).extract().response();

        System.out.println("Response Body: " + response.getBody().asString());
    }

}