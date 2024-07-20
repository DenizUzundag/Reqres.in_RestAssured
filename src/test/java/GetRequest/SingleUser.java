package GetRequest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SingleUser {

    @Test
    public void SingleUserRequest(){
        //https://reqres.in/api/users/2
        RestAssured.baseURI="https://reqres.in/api/";
        RestAssured.basePath="/users";

        Response response = given().
                pathParam("id",2)
        .when()
                .get("/{id}")
        .then()
                .log().all()
                .assertThat().statusCode(200)
               // .assertThat().body("data.first_name",equalTo("Janet"))
                .extract().response();

        String ContentType= response.getHeader("Content-Type");
        System.out.println("Content-Type: " + ContentType);
        ContentType.equals("application/json; charset=utf-8");

        // Status Code
        System.out.println("Status Code: " + response.getStatusCode());

        // Headers
        System.out.println("Headers: " + response.getHeaders());

        // Body
        System.out.println("Body: " + response.getBody().asString());


    }
}
