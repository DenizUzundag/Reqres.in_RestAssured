package PostRequest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.given;

public class CreateUser {
    @Test
    public void CreateUserRequest(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api/users";

        String bodyData = "{ \"name\": \"Deniz\", \"job\": \"TestLeader\" }";


        Response response = given()
                .header("Content-Type", "application/json")
                .body(bodyData)
                .when()
                .post()
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .body("name", equalTo("Deniz"))
                .body("job", equalTo("TestLeader"))
                .extract().response();

        // Yanıt gövdesinden verileri alma ve yazdırma
        System.out.println("Id: " + response.jsonPath().getString("id"));
        System.out.println("Name: " + response.jsonPath().getString("name"));
        System.out.println("Job: " + response.jsonPath().getString("job"));
        System.out.println("Created At: " + response.jsonPath().getString("createdAt"));

        // Yanıt gövdesini yazdırma
        System.out.println("Response Body: " + response.getBody().asString());

    }
}
