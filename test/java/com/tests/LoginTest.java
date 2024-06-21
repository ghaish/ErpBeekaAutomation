package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.BaseTest;
import com.pages.LoginPage;
import com.utilities.ExtentReportGenerator;
import com.utilities.ReadXLSData;

public class LoginTest extends BaseTest {
	public LoginPage lp;
	@Test(dataProviderClass = ReadXLSData.class, dataProvider = "testdata", retryAnalyzer = com.utilities.RetryAnalyzer.class)
	public void loginTest(String username, String password, String type) throws InterruptedException {
		ExtentTest etest = ExtentReportGenerator.getExtentTest();
		lp = new LoginPage(driver);
		lp.clear();
		lp.enterMail(username, password);
		if (type.equals("valid")) {
//			etest.log(Status.INFO, "Test data is of Valid username and Password");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("dashboard")));
		} else if (type.equals("invalid")) {
//			etest.log(Status.INFO, "Test data is of Invalid username and Password");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(), 'Invalid email or password.')]")));
		}
	}
}
