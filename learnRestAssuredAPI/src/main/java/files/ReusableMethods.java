package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

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

}
