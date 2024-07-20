package PutRequest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUser {

    @Test
    public void UpdateUserRequest(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api/users";

        String UpdateBody= "{ \"name\": \"Deniz2\", \"job\": \"TestLeader2\" }";
        Response response = given()
                .header("Content-Type","application/json")
                .body(UpdateBody)
        .when()
                .put("/2")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .body("name", equalTo("Deniz2"))
                .body("job", equalTo("TestLeader2"))
                .extract().response();

        System.out.println("Response Body: " + response.getBody().asString());

    }

}
