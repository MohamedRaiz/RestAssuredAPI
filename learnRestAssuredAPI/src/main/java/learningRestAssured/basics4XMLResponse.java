package learningRestAssured;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class basics4XMLResponse {

    @Test
    public void postData() throws IOException {

        String postData = generateStringFromResource("/Users/mohamedraiz/Desktop/Coding/RestAssuredAPI/postData.xml");
        //baseUrl or Hostname:
        RestAssured.baseURI = "http://216.10.245.166";
        Response res;
        res = given().
                queryParam("key", "qaclick123").
                body(postData).
                when().
                post("/maps/api/place/add/xml").
                then().assertThat().statusCode(200).and().
                contentType(ContentType.XML).
                extract().response();

        XmlPath xmlPath = ReusableMethods.rawToXML(res);
        Object extractedField = xmlPath.get("response.place_id");
        System.out.println(extractedField );


    }

    public static String  generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
