package com.rbhatt.selenium.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject(){
		
		//Reporter for adding all Metadata related to the Report
		String path = System.getProperty("user.dir")+"//ExtentReports//ExtentReport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Selenium Framework Report");
		reporter.config().setDocumentTitle("Test Results");
		
		//Attaching Reporter to the actual Report
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","Rishil Bhatt");
		return extent;
	}
}
