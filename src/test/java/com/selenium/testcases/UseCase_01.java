package com.selenium.testcases;

import org.testng.annotations.Test;

//import com.database.connection.DatabaseTesting;
import com.selenium.base.BaseTestUI;
/**
 * Use case with ID 01
 */

@Test
public class UseCase_01 extends BaseTestUI {
	
	//BaseTestUI base = new BaseTestUI();
    

	public UseCase_01() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * Create the test case
     *
     * @param testName name of the test case
     * @throws IOException 
     */
		
		//DatabaseTesting db = new DatabaseTesting();
	
	public void Case_01() throws Exception
		{  
        
		/**
		 * Use case read data from excel file
		 */
		
        //sendText("Search.Textbox", "Predix_search");
        
        //click("Login.SearchBtn");
        
        //sendTextWithEnter("Search.Textbox", "Predix_search");
		
		//click("Lookup.Icon");
		
		//checkExist("Lookup.Box");
		
		//sendTextWithEnter("Lookup.Box", "Predix_search");
		
		/**
		 * Database case (MySQL)
		 */
		
		//db.compareResultSetDb("query_actor", "exp_query_actor");
		
		/**
		 * Upload file case
		 */
		
		//ScrollToElement("ElementToMove.TextBox");
		
		//uploadFileByAutoIT("Upload.BrowseBtn","E:\\Automation_Java_Jmeter_C#_document\\AutoIT\\uploadscript.exe");
		
		/**
		 * Drag & Drop case
		 */
		
		DragDropFile("ElementToMove.TextBox", "MoveToElement.PlaceHolder");
		
	}
		
}