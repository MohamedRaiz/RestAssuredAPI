package learningRestAssured;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class basics7JiraAddingComments {


    @Test
    public void JiraApiComment() {

        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().
                header("Content-Type", "application/json ").
                header("Cookie", "JSESSIONID=" + ReusableMethods.getSessionKeyJira()).
                body("{\n" +
                        "  \"visibility\": \n" +
                        "  {\n" +
                        "    \"type\": \"role\",\n" +
                        "    \"value\": \"Administrators\"\n" +
                        "  },\n" +
                        "  \"body\": \"I have commented from REST API from IntelliJ.\"\n" +
                        "}").
                when().
                post("/rest/api/2/issue/10027/comment").
                then().statusCode(201).extract().response();

        JsonPath js = ReusableMethods.rawToJSON(res);
        String commentId = js.get("id");
        System.out.println(commentId);
    }

}
