import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class dataDrivenTest {

    //WHat we want to achieve:
    //First we want to get access to the excel file, then the sheet.
    //Once that is done, we want to identify the testcases columns by scanning the entire row.
    //Once column has been identified, we then scan the entire testcase column to identify purchase testcase row.
    //Once we get that, put all the data of that row and feed it into test

    public void getData(String testCaseName) throws IOException {
        //fileInputStream Argument
        ArrayList<String> a = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(".//ExampleData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int numberOfSheets = workbook.getNumberOfSheets();
        System.out.println(numberOfSheets);

        for(int i = 0; i < numberOfSheets; i ++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("WithData")) {
                //Now we have access to the sheet through this
                XSSFSheet sheet = workbook.getSheetAt(i);

                //We want to identifiy the testcases columns now.
                Iterator<Row> rows = sheet.iterator();
                //Go to the first row.
                Row firstRow = rows.next();
                //now that we have the first row, we need to read each and every cell to see which cell have the testcases value.
                Iterator<Cell> cellIterator = firstRow.cellIterator();
                //Now we have to read every cell value and compare with the testCases value. So here, we will use
                //hasNext() instead. this basically checks if the following next cell has a value and returns true if there is.
                //But this hasNext only checks. it does not move. Hence, to move, we must still use .next() method.

                //Declaring a variable to keep track of which column is the column which matches testCases.
                int k = 0;
                int column = 0;
                while(cellIterator.hasNext()){
                    //This means that it has moved to the first cell the first time. as this is in while loop, the second time, it goes
                    //into the 2nd cell and so on...
                    Cell value = cellIterator.next();
                    //now we have gotten the cell value. we need to convert to string and compare.
                    //this is done via the if condition
                    if(value.getStringCellValue().equalsIgnoreCase("TestCases")){
                        column = k;
                    }
                    k++;

                }
                System.out.println(column);
                //Once column has been identified, we then scan the entire testcase column to identify purchase testcase row.
                while(rows.hasNext()){
                    Row nextRow = rows.next();

                    //this basically means that for every row, get me the column of the cell where testcases was found.
                    if (nextRow.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {

                        //Inside here, means we have found purchase, now we want all the different column datas for purchase. So again, we
                        //would need to iterate the cells. this can be done via:
                        Iterator<Cell> cellValuesWanted = nextRow.cellIterator();
                        while(cellValuesWanted.hasNext()){
                            a.add(cellValuesWanted.next().getStringCellValue());
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
