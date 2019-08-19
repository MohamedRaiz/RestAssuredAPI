package learningRestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class basicsPostPart2  {
    @Test
    public void postAndDeleteData() {

        //baseUrl or Hostname:
        RestAssured.baseURI = "http://216.10.245.166";
        Response res;

        String bodyContent = "{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Frontline house\",\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"shoe park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}";
        res = given().
                queryParam("key","qaclick123").
                body(bodyContent).
                when().
                post("/maps/api/place/add/json").
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
