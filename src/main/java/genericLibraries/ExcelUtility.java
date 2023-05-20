package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This method contains reusable methods to perform operations on Excel
 * 
 * @author nanjj
 *
 */
public class ExcelUtility {

	private Workbook workbook;

	/**
	 * This method is used to initialize excel workbook
	 */
	public void excelInit(String excelPath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			workbook = WorkbookFactory.create(fis);
		}

		catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method returns single data ata a time from excel
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 */

	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) {
		return workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
	}

	/**
	 * This method is used to read data from excel in key value pairs
	 * @param sheetName
	 * @return
	 */
	
	public Map<String,String> getDataFromExcel(String sheetName, String expectedTestName) {
		Map<String,String> map = new HashMap<>();
		DataFormatter df = new DataFormatter();
		
		 Sheet sheet = workbook.getSheet(sheetName);
		 
		 for(int i=0; i<=sheet.getLastRowNum(); i++)
		 {
			 String actualTestName = df.formatCellValue(sheet.getRow(i).getCell(1));
		 if(actualTestName.equals(expectedTestName))
		 {
			 for (int j=i; j<=sheet.getLastRowNum(); j++)
			 {
				 String key = df.formatCellValue(sheet.getRow(j).getCell(2));
				 String value = df.formatCellValue(sheet.getRow(j).getCell(3));
				 
				 map.put(key,  value);
				 if(key.equals("####"))
					 break;
			 }
			 break;
		 }
	}
	return map;
	}
	/**
	 * This method is  used to write test case status into excel
	 */
	
	public void writeDataToExcel(String sheetName, String expectedTestName, String status, String excelPath)
	{
	Sheet sheet = workbook.getSheet(sheetName);
	
	for(int i=0; i<=sheet.getLastRowNum(); i++) {
		String actualTestName = sheet.getRow(1).getCell(1).getStringCellValue();
		if(actualTestName.equals(expectedTestName)) {
			sheet.getRow(i).getCell(4).setCellValue(status);
			break;
		}
	}
	FileOutputStream fos = null;
	try {
		fos = new FileOutputStream(excelPath);
	}
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	try {
		workbook.write(fos);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

	public void closeExcel() {
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}


