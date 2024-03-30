package com.tests;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.JournalCreationPage;
import com.pages.LoginPage;

public class JournalCreationTest extends BaseTest {
	JournalCreationPage jcp;
	LoginPage lp;
	Actions actions;
    
	@BeforeClass
	public void setupPage() throws InterruptedException {
		actions = new Actions(driver);
		lp = new LoginPage(driver);
		lp.clear();
		lp.enterMail("beekatech@gmail.com", "beekatech@2023");
		jcp = new JournalCreationPage(driver);
		jcp.vouchersElement.click();
		jcp.refresh();
		jcp.journal.click();
		jcp.addJournal.click();
	}

	@Test
	public void receiptVoucherCreationTest() throws InterruptedException {

		jcp.fillForm();
		Assert.assertTrue(jcp.journalCreatedMessage.isDisplayed(), "Confirmation message is not displayed");
	}
}
