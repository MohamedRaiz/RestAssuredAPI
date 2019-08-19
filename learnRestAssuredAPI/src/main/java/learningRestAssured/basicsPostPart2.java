package learningRestAssured;

import files.payload;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class basicsPostPart2  {

    Properties prop = new Properties();
   @BeforeTest
    public void getData() throws IOException {


       FileInputStream fis = new FileInputStream("/Users/mohamedraiz/Desktop/Coding/RestAssuredAPI/learnRestAssuredAPI/src/main/java/files/env.properties");
       prop.load(fis);
       prop.getProperty("HOST");
   }

    @Test
    public void postAndDeleteData() {

        //baseUrl or Hostname:
        RestAssured.baseURI = prop.getProperty("HOST");
        Response res;


        res = given().
                queryParam("key", prop.getProperty("KEY ")).
                body(payload.getPostData()).
                when().
                post(resources.placePostData()).
                then().assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("status",equalTo("OK")).and().
                extract().response();

        String responseString = res.asString();
        System.out.println(responseString);
        JsonPath js = new JsonPath(responseString); // this is basically a JSON now.
        String placeid = js.get("place_id");
        System.out.println(placeid);

        given().
                queryParam("key","qaclick123").
                body("{\"place_id\" : \"" + placeid+ " \" }" ).
                when().
                post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("status",equalTo("OK"));


    }

}
