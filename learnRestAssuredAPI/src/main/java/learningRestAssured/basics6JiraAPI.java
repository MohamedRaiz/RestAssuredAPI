package learningRestAssured;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class basics6JiraAPI {


    @Test
    public void JiraApi() {

        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().
                header("Content-Type", "application/json ").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKeyJira()).
                body("{\n" +
                        "\"fields\" : \n" +
                        "{\n" +
                        "\"project\" : \n" +
                        "{\n" +
                        "\"key\" : \"RES\"\n" +
                        "},\n" +
                        "\"summary\" : \"Debit Card Defect From IntelliJ\",\n" +
                        "\"description\" : \"Creating my first bug\",\n" +
                        "\"issuetype\" : \n" +
                        "{\n" +
                        "\"name\" : \"Bug\"\n" +
                        "}\n" +
                        "}\n" +
                        "}").
                when().
                post("/rest/api/2/issue").
                then().statusCode(201).extract().response();

        JsonPath js = ReusableMethods.rawToJSON(res);
        String id = js.get("id");
        System.out.println(id);

    }

}
