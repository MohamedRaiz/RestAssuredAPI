package learningRestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class basics4XMLResponse {

    @Test
    public void postData() throws IOException {

        String postData = generateStringFromResource("/Users/mohamedraiz/Desktop/Coding/RestAssuredAPI/postData.xml");
        //baseUrl or Hostname:
        RestAssured.baseURI = "http://216.10.245.166";

        given().
                queryParam("key", "qaclick123").
                body(postData).
                when().
                post("/maps/api/place/add/xml").
                then().assertThat().statusCode(200).and().
                contentType(ContentType.XML).and().
                body("status", equalTo("OK"));


        //Create a Place and Delete it. To delete, we need to grab the place_id and place it in the delete place request.


    }

    public static String  generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
