package com.rbhatt.selenium.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.rbhatt.selenium.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentText = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentText.set(test); // Unique Thread ID
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentText.get().log(Status.PASS, "Test method passed: " + result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentText.get().log(Status.FAIL, "Test method failed: " + result.getMethod().getMethodName());
		extentText.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String screenshotPath = null;
		try {
			screenshotPath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		extentText.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test method skipped: " + result.getMethod().getMethodName());
	}
	
	@Override
	public void onFinish(ITestContext result) {
		extent.flush();
	}
}
