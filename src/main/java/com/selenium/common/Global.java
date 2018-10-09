/**
 * Global variables which are used at all layers
 *
 * @author	Tran Viet Duc
 * @version	0.1
 */
package com.selenium.common;

public class Global {

	/**
	 * System variables
	 */
	public static String FolderRoot = null;
	public static String FolderScenario = null;
	public static String FolderSetting = null;
	public static String FolderData = null;
	public static String FileJarPath = null;

	/**
	 * Setting files
	 */
	public static String FileMessage = "settings/messages.properties";
	public static String FileEnv = "settings/env.properties";
	public static String FileLog4J = "settings/log4j.properties";
	public static String FileDataSource = "settings/data_source.properties";
	//public static String FileExecutionPlanCLI = null;
	public static String FileObjRepository = "object_repositories/object.properties";
	public static String FileExtentReport = "settings/extent-config.xml";

	
	/**
	 * Environment
	 */
	public static String UAT_PATH;
	public static int UAT_OBJECT_TIMEOUT = 20;
	
	/**
	 * Driver file
	 */

	public static String DriverFFrepo= "driver/geckodriver.exe";
	public static String DriverChromerepo = "driver/chromedriver.exe";
	public static String DriverIErepo = "";
	
	/**
	 * Test case
	 */
	
	public static String TestID = null;
	public static String CurrDataFileName = "data/TestData1.xlsx";
	public static String SheetName="Data";
}
