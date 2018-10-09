package com.selenium.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.selenium.base.BaseTestUI;
import com.selenium.utilities.ExtentManager;;

//**********************************************************************************************************
//Author: Onur Baskirt
//Modifier: Duc Tran
//Description: This is the main listener class.
//**********************************************************************************************************
public class TestListener extends BaseTestUI implements ITestListener {

	public TestListener() throws Exception {
		super();
	}

	// Extent Report Declarations
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Extent Report for Test Suite started!");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Extent Report for Test Suite is ending!"));
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " started!"));
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		test.set(extentTest);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {

		String method_name = result.getMethod().toString().trim();

		System.out.println((method_name + " failed!"));

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + method_name + System.currentTimeMillis() + ".png"));
			System.out.println("***Placed screen shot in " + System.getProperty("user.dir") + " ***");
			test.get().fail(result.getThrowable());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// File imageFile = ((TakesScreenshot)
		// driver).getScreenshotAs(OutputType.FILE);
		// String failureImageFileName = result.getMethod().getMethodName()
		// + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new
		// GregorianCalendar().getTime()) + ".png";
		// File failureImageFile = new File(System.getProperty("user.dir") +
		// "\\screenshots\\" + failureImageFileName);
		// try {
		// FileUtils.copyFile(imageFile, failureImageFile);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}
}
