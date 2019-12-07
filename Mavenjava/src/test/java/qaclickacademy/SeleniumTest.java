package qaclickacademy;

import org.testng.annotations.Test;

public class SeleniumTest {

    //trigger testng.xml from maven.
    //if we want to execute all test cases from test folder, we have did mvn test
    @Test
    public void BrowserAutomation() {

        System.out.println("Browser Automation");
    }

    @Test
    public void ElementsUI() {

        System.out.println("Elements UI");
    }

}
