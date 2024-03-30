package com.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.BaseTest;
import com.utilities.ExtentReportGenerator;

public class MyListeners extends BaseTest implements ITestListener{
	ExtentReports report = ExtentReportGenerator.getExtentReport();
	ExtentTest eTest;

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		eTest = report.createTest(testName);
		eTest.log(Status.INFO, testName+" execution started");
		ExtentReportGenerator.setExtentTest(eTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		eTest.log(Status.PASS, testName+" got Successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		eTest.log(Status.FAIL, testName+" got Failed");
		try {
			eTest.addScreenCaptureFromPath(takeScreenShot(testName,driver), testName);
		} catch (IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		eTest.log(Status.SKIP, testName+" got Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
	
	public String takeScreenShot(String testName, WebDriver driver) {
		File sourceScreenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destinationScreenShotFile = new File(System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png");
		try {
			FileUtils.copyFile(sourceScreenShotFile, destinationScreenShotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenShotFile.getAbsolutePath();
	}

}
