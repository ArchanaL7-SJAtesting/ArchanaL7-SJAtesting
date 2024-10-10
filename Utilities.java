package com.tutorialsninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
public static final int IMPLICIT_WAIT_TIME=20 ;
public static final int PAGE_LOAD_TIME=20;
	
public static String generateEmailWithTimeStamp() {
	
Date date = new Date();
	    
 String timestamp = date.toString().replace(" ", "").replace(":", "_");
	    
 return "aln.sap.st" + timestamp + "@gmail.com";
	
	}
	
public static Object[][] getTestDataFromExcel(String sheetName) {

File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");

XSSFWorkbook workBook =null;

try(FileInputStream fisExcel = new FileInputStream(excelFile))
{    

workBook = new XSSFWorkbook(fisExcel);

}

catch(Throwable e)
{
e.printStackTrace();
}

if (workBook == null) {
    throw new RuntimeException("Error: Unable to read Excel workbook");
}

XSSFSheet sheet = workBook.getSheet(sheetName);

    int rows = sheet.getLastRowNum()+1;
    int cols = sheet.getRow(0).getLastCellNum();

    Object[][] data = new Object[rows][cols];
    
    for (int i = 0; i < rows; i++) {
        XSSFRow row = sheet.getRow(i);
        if (row == null) {
            continue; // Skip empty rows
        }


        for (int j = 0; j < cols; j++) {
            XSSFCell cell = row.getCell(j);
            CellType cellType = cell.getCellType();

            switch (cellType) {
                case STRING:
                    data[i][j] = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    data[i][j] = cell.getNumericCellValue();
                    break;
                case BOOLEAN:
                    data[i][j] = cell.getBooleanCellValue();
                    break;
                default:
                    data[i][j] = "";
            }
        }
    }

    return data;
} 
}