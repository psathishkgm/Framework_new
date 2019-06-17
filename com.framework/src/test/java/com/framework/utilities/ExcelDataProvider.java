package com.framework.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	
	
	XSSFWorkbook wb;
	
	public ExcelDataProvider() throws Exception {
		
		File src = new File("./Test_Data/Data.xlsx");
		
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		
	}
	
	public String getStringdata(int sheetIndex, int row, int coloumn) {
		
		return wb.getSheetAt(sheetIndex).getRow(row).getCell(coloumn).getStringCellValue();
	}
	
    public String getStringdata(String sheetName, int row, int coloumn) { // method overloading(type of parameter has been changed)
		
		return wb.getSheet(sheetName).getRow(row).getCell(coloumn).getStringCellValue();
	}
    
   public double getNumericdata(String sheetName, int row, int coloumn) { 
		
		return wb.getSheet(sheetName).getRow(row).getCell(coloumn).getNumericCellValue();
	}


}
