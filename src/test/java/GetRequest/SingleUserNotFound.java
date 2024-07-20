package GetRequest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class SingleUserNotFound {

    @Test
    public void UserNotFoundRequest(){
        //https://reqres.in/api/users/23

        RestAssured.baseURI="https://reqres.in/api/";
        RestAssured.basePath="/users";

        Response response = given().
                pathParam("id", 23)
        .when()
            .get("/{id}")
        .then()
                .statusCode(404)
                .extract().response();

        // Headers
        System.out.println("Status Code: " + response.statusCode());
    }

}