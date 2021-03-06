package learningRestAssured;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class StaticJson {

    @Test
    public void AddBook() throws IOException {

        //baseUrl or Hostname:
        RestAssured.baseURI = "http://216.10.245.166";
        Response res;
        res = given().
                header("Content-Type", "application/json").
                body(generateStringFromResource("/Users/mohamedraiz/Desktop/Coding/RestAssuredAPI/addBookDetails.json")).
                when().
                post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response();

        JsonPath js = ReusableMethods.rawToJSON(res);
        String id = js.get("ID");
        System.out.println(id);

        // This is to just delete the book back so the test will always pass and book will not be kept in the library.

    }

    public static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
