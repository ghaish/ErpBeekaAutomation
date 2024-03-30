package com.tests;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.LoginPage;
import com.pages.PaymentCreationPage;


public class PaymentCreationTest extends BaseTest{
	PaymentCreationPage pcp;
	LoginPage lp;
	Actions actions;

	@BeforeClass
	public void setupPage() throws InterruptedException {
		actions = new Actions(driver);
		lp = new LoginPage(driver);
		lp.clear();
		lp.enterMail("beekatech@gmail.com", "beekatech@2023");
		pcp = new PaymentCreationPage(driver);
		pcp.vouchersElement.click();
		pcp.refresh();
		pcp.payment.click();
		pcp.addPayment.click();
	}
	@Test
	public void paymentVoucherCreationTest() throws InterruptedException {

		pcp.fillForm();
		Assert.assertTrue(pcp.paymentCreatedMessage.isDisplayed(), "Confirmation message is not displayed");
	}
}
