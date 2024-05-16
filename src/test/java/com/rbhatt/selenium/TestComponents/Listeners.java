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
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test method started: " + result.getMethod().getMethodName());
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test method passed: " + result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test method failed: " + result.getMethod().getMethodName());
		test.fail(result.getThrowable());
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
		test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
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
