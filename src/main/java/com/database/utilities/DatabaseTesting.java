/**
 * 
 */
package com.database.utilities;

import java.util.ArrayList;

import com.selenium.common.ExcelData;

/**
 * @author Tran Viet Duc
 *
 */
public class DatabaseTesting extends DataBaseConnector {
	
	public boolean compareResultSetDb (String query, String expect_db){
				
		String query_string = ExcelData.getCellValue(query);
		
		ArrayList<String> resultdb = executeSQLQuery_List(query_string);
		
		String exp_query = ExcelData.getCellValue(expect_db);
		
		for (String i : resultdb){
			
			if(i.equalsIgnoreCase(exp_query))
								
				break;
				
		}
		return true;
	}

}
