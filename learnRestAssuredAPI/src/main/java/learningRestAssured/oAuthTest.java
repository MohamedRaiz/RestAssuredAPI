package learningRestAssured;

import POJO.GetCourse;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class oAuthTest {

    public static void main(String[] args) throws InterruptedException {

        //First step for Selenium
        System.setProperty("webdriver.chrome.driver","../../../../../../usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("mohamedraiz92");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
        //we added a thread.sleep so that the password key in page is loaded in time before we put in the password.
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Mohamedraiz92!");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);

        //In selenium, we can get the currentURL via:
        String url = driver.getCurrentUrl();
        String partialCode = url.split("code=")[1];
        String actualCode = partialCode.split("&scope")[0];
        System.out.println(actualCode);

        //Here we are trying to get the code to be generated.
        String accessTokenResponse = given().urlEncodingEnabled(false).
                queryParams("code", actualCode).
                queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
                queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").
                queryParams("grant_type", "authorization_code").
                when().log().all().
                post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath jsonPath = new JsonPath(accessTokenResponse);
        String accessToken = jsonPath.get("access_token");


        GetCourse response = given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON).
                when().
                get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

        System.out.println(response.getCourses());
        System.out.println(response.getInstructor());
    }

}
