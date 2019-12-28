package qaclickacademy;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class basics5MoreOnExtractingJson {

    @Test
    public void Test5() {

        //baseUrl or Host
        RestAssured.baseURI = "https://maps.googleapis.com";

        //we need to tell what are given. Parameters and Resources. Need to put both key and values.
        //what we already have goes into given.
        Response res = given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "500").
                param("key", "AIzaSyD9BxP-JWompJwYS9drppTXkrrJztTHFLI").
                log().all().
                when().get("/maps/api/place/nearbysearch/json").
                then().assertThat().
                statusCode(200).and().contentType(ContentType.JSON)
                .and().body("results[0].name", equalTo("Sydney"))
                .and().body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"))
                .and().header("Server", "scaffolding on HTTPServer2").
                        extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);

        //So now, we want to loop through the 20 response from google, and get the place in every array element.

        int arraySize = js.get("results.size()");
        System.out.println(arraySize);
        for (int i = 0; i < arraySize; i++) {
            Object name = js.get("results[" + i + "].name");
            System.out.println(name);
        }


    }
}
