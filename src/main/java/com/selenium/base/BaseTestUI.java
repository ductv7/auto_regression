package com.selenium.base;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.selenium.common.*;

/**
 * @author Tran Viet Duc
 * 
 * @version: 0.1
 *
 */
public class BaseTestUI extends BasePage {

	// private Logger logger = Logger.getLogger(BaseTestUI.class.getName());

	public BaseTestUI() throws Exception {

		bp = new BasicProperties();

		pros_env = bp.load(Global.FileEnv);

		pros_obj = bp.load(Global.FileObjRepository);

		this.driver = initializeDriver();

	}

	private WebDriver initializeDriver() throws IOException {

		if (pros_env.getProperty("REMOTE_URL").isEmpty())

		{
			if (pros_env.getProperty("BROWSER").equalsIgnoreCase("firefox")) {

				System.setProperty("webdriver.gecko.driver", Global.DriverFFrepo);
				driver = new FirefoxDriver();

			} else if (pros_env.getProperty("BROWSER").equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", Global.DriverChromerepo);
				driver = new ChromeDriver();

			} else {

				driver = new InternetExplorerDriver();
			}

		}

		else {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(pros_env.getProperty("BROWSER"));
			capabilities.setPlatform(Platform.fromString(pros_env.getProperty("PLATFORM")));

			driver = new RemoteWebDriver(new URL(pros_env.getProperty("REMOTE_URL")), capabilities);

		}

		return driver;

	}

	protected By getBy(String objPath) {
		By r = null;
		if (pros_obj.getProperty(objPath + ".Id") != null) {
			r = By.id(pros_obj.getProperty(objPath + ".Id"));
			// } else if (pros_obj.getProperty(objPath + ".Name") != null) {
			// r = By.name(pros_obj);
		} else if (pros_obj.getProperty(objPath + ".TagName") != null) {
			r = By.tagName(pros_obj.getProperty(objPath + ".TagName"));
		} else if (pros_obj.getProperty(objPath + ".LinkText") != null) {
			r = By.linkText(pros_obj.getProperty(objPath + ".LinkText"));
		} else if (pros_obj.getProperty(objPath + ".PartialLinkText") != null) {
			r = By.partialLinkText(pros_obj.getProperty(objPath + ".PartialLinkText"));
		} else if (pros_obj.getProperty(objPath + ".CssSelector") != null) {
			r = By.cssSelector(pros_obj.getProperty(objPath + ".CssSelector"));
		} else if (pros_obj.getProperty(objPath + ".ClassName") != null) {
			r = By.className(pros_obj.getProperty(objPath + ".ClassName"));
		} else if (pros_obj.getProperty(objPath + ".XPath") != null) {
			r = By.xpath(pros_obj.getProperty(objPath + ".XPath"));
		} else if (pros_obj.getProperty(objPath) != null) {
			r = By.id(pros_obj.getProperty(objPath));
		}

		return r;
	}

	protected Boolean getElement(String objPath) {
		Boolean r = false;
		try {
			ele = driver.findElement(getBy(objPath));
			r = true;
		} catch (Exception e) {

		}

		return r;
	}

	@BeforeTest
	public void navigateBrowser() throws IOException {

		driver.get(pros_env.getProperty("URL"));

		driver.manage().window().maximize();

	}
	
	@AfterTest
	public void tearDown(){
		
		driver.quit();
	}

	/**
	 * 
	 * @param objPath:
	 *            object element
	 * @param fieldName:
	 *            value will get from excel file
	 * @throws FileNotFoundException
	 */

	public void sendText(String objPath, String key) throws FileNotFoundException {

		String input = ExcelData.getCellValueData(key);

		driver.findElement(getBy(objPath)).sendKeys(input);
	}

	public void sendTextWithEnter(String objPath, String key) throws FileNotFoundException {

		String input = ExcelData.getCellValueData(key);

		driver.findElement(getBy(objPath)).sendKeys(input + Keys.ENTER);
	}

	public void click(String objPath) {

		try {
			driver.findElement(getBy(objPath)).click();
		} catch (Exception e) {
			Logger.getLogger(objPath);
		}
	}

	public List<WebElement> getElements(String objPath) {

		List<WebElement> r = null;
		try {

			r = driver.findElements(getBy(objPath));

		} catch (Exception e) {

			e.getMessage();
		}

		return r;
	}

	public boolean checkExist(String objPath) {

		if (driver.findElement(getBy(objPath)).isDisplayed())
			;

		return true;
	}

	public void doubleClick(String objPath) {

		Actions act = new Actions(driver);

		WebElement ele = driver.findElement(getBy(objPath));

		act.doubleClick(ele);

	}

	public void DragDropFile(String objPath_elementToMove, String objPath_moveToElement) {
		
		/**
		 * dragAndDrop(Sourcelocator, Destinationlocator)
		   dragAndDropBy(Sourcelocator, x-axis pixel of Destinationlocator, y-axis pixel of Destinationlocator)

		 */

		WebElement elementToMove = driver.findElement(getBy(objPath_elementToMove));

		WebElement moveToElement = driver.findElement(getBy(objPath_moveToElement));

		(new Actions(driver)).dragAndDrop(elementToMove, moveToElement).perform();
		
		//By pixel
		
		//(new Actions(driver)).dragAndDropBy(From,135,40).perform();
	}

	public void ScrollToElement(String objPath) throws Exception {

		ele = driver.findElement(getBy(objPath));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", ele);

		Thread.sleep(1000);
	}

	public void uploadFileByAutoIT(String objPath, String command) throws Exception {

		// command in here is location of .exe file of autoIT

		driver.findElement(getBy(objPath)).click();

		// ((JavascriptExecutor) driver).executeScript("arguments[0].click()",
		// ele);

		Thread.sleep(5000);

		Runtime.getRuntime().exec("E:\\Automation_Java_Jmeter_C#_document\\AutoIT\\uploadscript.exe");

		assertTrue(ele.findElement(By.xpath("//*[contains(text(),'upload')]")).isDisplayed());
	}
}