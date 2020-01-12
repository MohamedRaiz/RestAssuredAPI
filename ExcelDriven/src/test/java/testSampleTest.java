import java.io.IOException;
import java.util.ArrayList;

public class testSampleTest {

    public static void main(String[] arg) throws IOException {

        dataDrivenTest dataDrivenTest = new dataDrivenTest();
        ArrayList<String> datas = dataDrivenTest.getData("Add Profile");
        System.out.println(datas.get(0));
        System.out.println(datas.get(1));

    }
}
