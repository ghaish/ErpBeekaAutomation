package com.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportGenerator {
	private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static void setExtentTest(ExtentTest extentTest) {
        extentTestThreadLocal.set(extentTest);
    }

    public static ExtentTest getExtentTest() {
        return extentTestThreadLocal.get();
    }
	public static ExtentReports getExtentReport() {
		ExtentReports report = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+"\\ExtentReports\\eReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		report.attachReporter(sparkReporter);
		return report;
	}

}
