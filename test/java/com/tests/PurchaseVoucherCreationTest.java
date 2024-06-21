package com.tests;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.base.BaseTest;
import com.pages.LoginPage;
import com.pages.PostingSearchPage;
import com.pages.PurchaseCreationPage;
import com.pages.ReceiptCreationPage;
import com.pages.ReceiptListPage;
import com.pages.VouchersSidebar;
import com.utilities.ExtentReportGenerator;

public class PurchaseVoucherCreationTest extends BaseTest {
	
	public ExtentTest etest;
	public PurchaseCreationPage pcp;
	public VouchersSidebar sb;
	
	
	LoginPage lp;
	@BeforeClass
	public void setupPage() throws InterruptedException {
		etest = ExtentReportGenerator.getExtentTest();
		lp = new LoginPage(driver);
		sb = new VouchersSidebar(driver);
		pcp = new PurchaseCreationPage(driver);
		lp.clear();
		lp.enterMail("beekatech@gmail.com", "beekatech@2023");
		sb.vouchersTab.click();
//		rcp.refresh();
		sb.Purchase.click();
		sb.addPurchase.click();
		System.out.println("setup is called.....");
		
	}
	
	@Test()
	public void purchaseVoucherCreationTest() throws InterruptedException {
		pcp.fillForm();
	}
}
