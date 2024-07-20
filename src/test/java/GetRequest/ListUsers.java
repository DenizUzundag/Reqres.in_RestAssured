package GetRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

public class ListUsers {

    @Test
    public void ListUserRequest(){

        RestAssured.baseURI="https://reqres.in/api/";
        RestAssured.basePath="/users";

        Response response =given().
                queryParam("page",2).
        when().
                get().
        then().
                log().all().
                assertThat().statusCode(200).
                assertThat().statusLine("HTTP/1.1 200 OK").
                assertThat().body("data.email",hasItem("michael.lawson@reqres.in")).
                assertThat().body("page",equalTo(2)).
                assertThat().body("data",not(empty())).
                assertThat().contentType("application/json; charset=utf-8").
                extract().response();

               List<String> Emails= response.jsonPath().getList("data.email");
               assertThat(Emails, everyItem(containsString("@reqres.in")));


    }
}
