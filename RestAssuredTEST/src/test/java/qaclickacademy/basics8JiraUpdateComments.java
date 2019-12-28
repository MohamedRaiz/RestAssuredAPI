package qaclickacademy;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class basics8JiraUpdateComments {


    @Test
    public void JiraApiCommentUpdate() {

        String commentId = ReusableMethods.JiraApiCommentAdd();

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
                        "  \"body\": \"I have updated the comment from REST API from IntelliJ after it was created.\"\n" +
                        "}").
                when().
                put("/rest/api/2/issue/10027/comment/" + commentId).
                then().statusCode(200).extract().response();

    }

}
