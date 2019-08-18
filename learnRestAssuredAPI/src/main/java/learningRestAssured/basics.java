package learningRestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class basics {

    @Test
    public void Test1() {

        //baseUrl or Host
        RestAssured.baseURI="https://maps.googleapis.com";

        //we need to tell what are given. Parameters and Resources. Need to put both key and values.
        //what we already have goes into given.
        given().
                param("location","-33.8670522,151.1957362").
                param("radius", "500").
                param("key","AIzaSyD9BxP-JWompJwYS9drppTXkrrJztTHFLI").
                when().get("/maps/api/place/nearbysearch/json").
                then().assertThat().
                statusCode(200).and().contentType(ContentType.JSON)
                .and().body("results[0].name",equalTo("Sydney"))
                .and().body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"))
                .and().header("Server","scaffolding on HTTPServer2");





    }
}
