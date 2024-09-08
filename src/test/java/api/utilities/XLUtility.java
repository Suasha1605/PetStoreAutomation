package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	String path;
	
	
	public FileInputStream fs;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	
	public XLUtility(String path) {
		
		
		this.path=path;
		
		
	}
	
	
	public int getRowCount(String sheetname) throws IOException {
		
		fs= new FileInputStream(path);
		workbook = new XSSFWorkbook(fs);
		sheet = workbook.getSheet(sheetname);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		
		return rowCount;
		
		
	}   
	
	public int getcellCount(String sheetname, int rownum) throws IOException {
		
		fs= new FileInputStream(path);
		workbook = new XSSFWorkbook(fs);
		sheet = workbook.getSheet(sheetname);
		XSSFRow row = sheet.getRow(rownum);
		
		int cellCount= row.getLastCellNum();
		workbook.close();
		
		return cellCount;
		
		
	} 
	
	
	public String getCellData(String sheetname,int rownum ,int colnum) throws IOException {
		
		fs= new FileInputStream(path);
		workbook = new XSSFWorkbook(fs);
		sheet = workbook.getSheet(sheetname);
		XSSFRow row = sheet.getRow(rownum);
		XSSFCell cell =row.getCell(colnum);
		
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try {
			
			data =formatter.formatCellValue(cell);//always return data in string format
			
		}
		catch(Exception e) {
			
			data="";
		}
		
		workbook.close();
		fs.close();
		return data;
		
		
		
	}
	
	

}
