package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ReadXLSData {
	@DataProvider(name="testdata")
	public String[][] getData(Method m) throws EncryptedDocumentException, InvalidFormatException, IOException {
		String excelsheetname = m.getName();
		Workbook wb;
		File f = new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\testdata\\testdata.xls");
		FileInputStream fis = new FileInputStream(f);
		System.out.println(fis);
		try {
			 wb = WorkbookFactory.create(fis);
		}
		catch(IOException e) {
			 wb = new HSSFWorkbook();
		}
		Sheet sheetname = wb.getSheet(excelsheetname);
		
		// getting rows and columns
		int totalRows = sheetname.getLastRowNum();
		Row rowCells = sheetname.getRow(0);
		int totalCols = rowCells.getLastCellNum();
		
		String testData[][] = new String[totalRows][totalCols]; 
		
		DataFormatter format = new DataFormatter();
		for(int i=1;i<=totalRows;i++) {
			for(int j=0;j<totalCols;j++) {
				testData[i-1][j]=format.formatCellValue(sheetname.getRow(i).getCell(j));
			}
		}
		return testData;
	}

}
