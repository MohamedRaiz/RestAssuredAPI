package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReusableMethods {

    public static XmlPath rawToXML(Response res) {

        //Extracting Response and Display
        String response = res.asString();
        //Converting to XML so that we can parse through.
        XmlPath xmlPath = new XmlPath(response);
        return xmlPath;
    }

    public static JsonPath rawToJSON(Response res) {

        //Extracting Response and Display
        String response = res.asString();
        //Converting to XML so that we can parse through.
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }

    public static String getSessionKeyJira() {
        //Creating Session.
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().
                header("Content-Type", "application/json ").
                body("{ \n" +
                        "\"username\": \"mohamed_raiz\", \n" +
                        "\"password\": \"Mrbar222!\"\n" +
                        "}").
                when().
                post("/rest/auth/1/session").
                then().statusCode(200).extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);
        String sessionId = js.get("session.value");
        System.out.println(sessionId);
        return sessionId;

    }

    public static String JiraApiCommentAdd() {

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
                        "  \"body\": \"I have commented from REST API from IntelliJ Again.\"\n" +
                        "}").
                when().
                post("/rest/api/2/issue/10027/comment").
                then().statusCode(201).extract().response();

        JsonPath js = ReusableMethods.rawToJSON(res);
        String commentId = js.get("id");
        System.out.println(commentId);
        return commentId;
    }

}

