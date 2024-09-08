package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider //(name="data")
	public String[][] getAllUserDataFromXL() throws IOException {
		
		String path =System.getProperty("user.dir")+"//test_data//userdata.xlsx";
		
		XLUtility xl = new XLUtility(path);
		
	int row=	xl.getRowCount("sheet1");
	int column=	xl.getcellCount("sheet1", 1);
	
	String userAPIData[][] = new String[row][column];
	
	
	for(int i=1; i<=row; i++) {
		
		for(int j=0; j<column; j++) {
			
			userAPIData[i-1][j]= xl.getCellData("sheet1", i, j);
		}
	}
	return userAPIData;
		
		
	}
	

}
