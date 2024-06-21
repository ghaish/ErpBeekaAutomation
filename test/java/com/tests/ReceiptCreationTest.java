package com.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.base.BaseTest;
import com.pages.LoginPage;
import com.pages.PostingSearchPage;
import com.pages.ReceiptCreationPage;
import com.pages.ReceiptListPage;
import com.utilities.ExtentReportGenerator;
import com.utilities.GetCoordinates;
import com.utilities.ReadXLSData;

public class ReceiptCreationTest extends BaseTest {
	public ReceiptCreationPage rcp;
	public ReceiptListPage rlp;
	public PostingSearchPage psp;
	public ExtentTest etest;

	LoginPage lp;
	Actions actions;
	String failureMessageForAssertion;
	String successfullLogMessage;
	

	@BeforeClass
	public void setupPage() throws InterruptedException {
		etest = ExtentReportGenerator.getExtentTest();
		actions = new Actions(driver);
		lp = new LoginPage(driver);
		rcp = new ReceiptCreationPage(driver);
		rlp = new ReceiptListPage(driver);
		psp = new PostingSearchPage(driver);
		
		lp.clear();
		lp.enterMail("beekatech@gmail.com", "beekatech@2023");
		rcp.vouchersElement.click();
//		rcp.refresh();
		rcp.receipt.click();
		rcp.addReceipt.click();
		System.out.println("setup is called.....");
		
	}
	
	 @DataProvider(name = "paymentModes")
	    public Object[][] paymentModes() {
	        return new Object[][] {
	            {"CASH"},
	            {"Bank"}
	        };
	    }

//	@Test(dataProvider = "paymentModes") retryAnalyzer = com.utilities.RetryAnalyzer.class
	@Test()
	public void receiptVoucherCreationTestOnCashModeOfPayment() throws InterruptedException {
//		try{
			int totDebit = 0, totCredit = 0;
			List<String> customerList = rcp.getCustomersList();
			System.out.print(customerList+"customer list----");
			String paymentMode = "CASH";
			String vno = rcp.fillForm(paymentMode);
			Assert.assertTrue(rcp.receiptCreatedMessage.isDisplayed(), "Confirmation message is not displayed");
			
			
	//		rcp.OKButton.click();
	//		js.executeScript("arguments[0].scrollIntoView();", rcp.receiptTitle);
	//		rcp.receiptList.click();
	//		js.executeScript("arguments[0].scrollIntoView();", rlp.lastPage);
	//		Point coForLastPageButton = GetCoordinates.getCoordinatesWithinElement(rlp.lastPage, 0.1, 0.1);
	//		actions.moveToElement(rlp.lastPage, coForLastPageButton.getX(), coForLastPageButton.getY()).click().build()
	//				.perform();
	//		WebElement table = driver.findElement(By.cssSelector(".table"));
	//
	//		WebElement lastRow = table.findElement(By.cssSelector("tbody tr:last-child"));
	//
	//		// Find the last column in the last row (voucher number column)
	//		WebElement lastColumn = lastRow.findElement(By.cssSelector("td:first-child"));
	//
	//		// Get the text of the last column (voucher number value)
	//		String voucherNumber = lastColumn.getText();
	//		System.out.println("Last column (voucher number) value: --------" + voucherNumber);
	
			
//			String lastCreatedVoucherNumber = rcp.getLastCreatedVoucherNo();
			
	//		Point coForMasterTab = GetCoordinates.getCoordinatesWithinElement(rcp.masterTab, 0.1, 0.1);
	//		actions.moveToElement(rcp.masterTab, coForMasterTab.getX(), coForMasterTab.getY()).click().build().perform();
			
			rcp.searchForPosting(vno);
	//		rcp.clickMasterTab();
	//		rcp.posting.click();
	//		rcp.postingSubMenu.click();
	//		Thread.sleep(5000);
	//		psp.searchQueryInput.click();
	//		psp.searchQueryInput.clear();
	////		psp.searchQueryInput.sendKeys(voucherNumber);
	//		psp.searchQueryInput.sendKeys(lastCreatedVoucherNumber);
	//		// Print the voucher number
	//		psp.searchButton.click();
	
			// Locate the table element
			WebElement postingTable = driver.findElement(By.xpath("//table"));
	
			// Locate the rows of the table
			List<WebElement> rows = postingTable.findElements(By.tagName("tr"));
			System.out.println("rows------" + rows);
	
			// Get the second row (first row contains headers)
			WebElement secondRow = rows.get(1);
			System.out.println("secondroe------" + secondRow);
	
			// Locate the cells of the second row
			List<WebElement> cells = secondRow.findElements(By.tagName("td"));
			System.out.println("List------" + cells);
			String[][] twoDimArray = new String[3][3];
			List<WebElement> accountTypes = cells.get(0).findElements(By.tagName("span"));
			int i=0, j=0, k=0;
			for (WebElement aT : accountTypes) {
				String accType = aT.getText().trim();
				twoDimArray[i][0] = accType;
				i++;
				System.out.println(accType + " acctype");
	
			}
			
			List<WebElement> debitedAmounts = cells.get(1).findElements(By.tagName("span"));
			for (WebElement dA : debitedAmounts) {
				String debAmt = dA.getText().trim();
				System.out.println(debAmt + " debAmt");
				twoDimArray[j][1] = debAmt;
				j++;
				totDebit+=Integer.parseInt(debAmt);
			}
			
			List<WebElement> creditedAmounts = cells.get(2).findElements(By.tagName("span"));
			for (WebElement cA : creditedAmounts) {
				String credAmt = cA.getText().trim();
				totCredit+=Integer.parseInt(credAmt);
				twoDimArray[k][2] = credAmt;
				k++;
				System.out.println(credAmt + " credAmt");
			}
			System.out.println(customerList+"customer list----");
			for (String[] row : twoDimArray) {
	            for (String element : row) {
	                System.out.print(element + " ");
	            }
	            System.out.println(); // Move to the next line after printing each row
	        }
			Assert.assertEquals(totDebit, totCredit);
	
	//		etest.log(Status.PASS, "Total debit and Total Credit are same");
	
			for (String[] row : twoDimArray) {
				if(row[0] != null) {
				    String accountType = row[0].trim();
				    String debited = row[1].trim();
				    String credited = row[2].trim();
		
				    if (accountType.equals("CASH A/C")) {
				    	failureMessageForAssertion = accountType + "Cash A/C is not Debited";
			        	successfullLogMessage = accountType + "Account is Debited succcessfully";
				        if (credited.equals("0") && !debited.equals("0")) {
				        	Assert.assertTrue(true,failureMessageForAssertion);
//				        	etest.log(Status.PASS, successfullLogMessage);
				        } else {
				        	Assert.assertFalse(false);
//				        	etest.log(Status.FAIL, failureMessageForAssertion);
				        }
				    }
		
				    if (customerList.contains(accountType)) {
				    	failureMessageForAssertion = accountType + "Account is not credited";
			        	successfullLogMessage = accountType + "Account is credited succcessfully";
			        	
				        if (!credited.equals("0") && debited.equals("0")) {
				        	Assert.assertTrue(true,failureMessageForAssertion);
//				        	etest.log(Status.PASS, successfullLogMessage);
				        } else {
				        	Assert.assertFalse(false);
//				        	etest.log(Status.FAIL, failureMessageForAssertion);
				        }
				    }
				}
			}
			
			Thread.sleep(5000);
		
//			}catch(StaleElementReferenceException e) {
//				driver.navigate().refresh();
//			}
	}
	@Test
	public void receiptVoucherCreationTestOBankModeOfPayment() throws InterruptedException {
//		try{
			int totDebit = 0, totCredit = 0;
			List<String> customerList = rcp.getCustomersList();
			System.out.print(customerList+"customer list----");
			String paymentMode = "Bank";
			String vno = rcp.fillForm(paymentMode);
			Assert.assertTrue(rcp.receiptCreatedMessage.isDisplayed(), "Confirmation message is not displayed");
			
			
	//		rcp.OKButton.click();
	//		js.executeScript("arguments[0].scrollIntoView();", rcp.receiptTitle);
	//		rcp.receiptList.click();
	//		js.executeScript("arguments[0].scrollIntoView();", rlp.lastPage);
	//		Point coForLastPageButton = GetCoordinates.getCoordinatesWithinElement(rlp.lastPage, 0.1, 0.1);
	//		actions.moveToElement(rlp.lastPage, coForLastPageButton.getX(), coForLastPageButton.getY()).click().build()
	//				.perform();
	//		WebElement table = driver.findElement(By.cssSelector(".table"));
	//
	//		WebElement lastRow = table.findElement(By.cssSelector("tbody tr:last-child"));
	//
	//		// Find the last column in the last row (voucher number column)
	//		WebElement lastColumn = lastRow.findElement(By.cssSelector("td:first-child"));
	//
	//		// Get the text of the last column (voucher number value)
	//		String voucherNumber = lastColumn.getText();
	//		System.out.println("Last column (voucher number) value: --------" + voucherNumber);
	
			
			
			
	//		Point coForMasterTab = GetCoordinates.getCoordinatesWithinElement(rcp.masterTab, 0.1, 0.1);
	//		actions.moveToElement(rcp.masterTab, coForMasterTab.getX(), coForMasterTab.getY()).click().build().perform();
			
			rcp.searchForPosting(vno);
	//		rcp.clickMasterTab();
	//		rcp.posting.click();
	//		rcp.postingSubMenu.click();
	//		Thread.sleep(5000);
	//		psp.searchQueryInput.click();
	//		psp.searchQueryInput.clear();
	////		psp.searchQueryInput.sendKeys(voucherNumber);
	//		psp.searchQueryInput.sendKeys(lastCreatedVoucherNumber);
	//		// Print the voucher number
	//		psp.searchButton.click();
	
			// Locate the table element
			WebElement postingTable = driver.findElement(By.xpath("//table"));
	
			// Locate the rows of the table
			List<WebElement> rows = postingTable.findElements(By.tagName("tr"));
			System.out.println("rows------" + rows);
	
			// Get the second row (first row contains headers)
			WebElement secondRow = rows.get(1);
			System.out.println("secondroe------" + secondRow);
	
			// Locate the cells of the second row
			List<WebElement> cells = secondRow.findElements(By.tagName("td"));
			System.out.println("List------" + cells);
			String[][] twoDimArray = new String[3][3];
			List<WebElement> accountTypes = cells.get(0).findElements(By.tagName("span"));
			int i=0, j=0, k=0;
			for (WebElement aT : accountTypes) {
				String accType = aT.getText().trim();
				twoDimArray[i][0] = accType;
				i++;
				System.out.println(accType + " acctype");
	
			}
			
			List<WebElement> debitedAmounts = cells.get(1).findElements(By.tagName("span"));
			for (WebElement dA : debitedAmounts) {
				String debAmt = dA.getText().trim();
				System.out.println(debAmt + " debAmt");
				twoDimArray[j][1] = debAmt;
				j++;
				totDebit+=Integer.parseInt(debAmt);
			}
			
			List<WebElement> creditedAmounts = cells.get(2).findElements(By.tagName("span"));
			for (WebElement cA : creditedAmounts) {
				String credAmt = cA.getText().trim();
				totCredit+=Integer.parseInt(credAmt);
				twoDimArray[k][2] = credAmt;
				k++;
				System.out.println(credAmt + " credAmt");
			}
			System.out.println(customerList+"customer list----");
			for (String[] row : twoDimArray) {
	            for (String element : row) {
	                System.out.print(element + " ");
	            }
	            System.out.println(); // Move to the next line after printing each row
	        }
			Assert.assertEquals(totDebit, totCredit);
	
	//		etest.log(Status.PASS, "Total debit and Total Credit are same");
	
			for (String[] row : twoDimArray) {
				if(row[0] != null) {
				    String accountType = row[0].trim();
				    String debited = row[1].trim();
				    String credited = row[2].trim();
		
				    if (accountType.equals("CASH A/C")) {
				    	failureMessageForAssertion = accountType + "Cash A/C is not Debited";
			        	successfullLogMessage = accountType + "Account is Debited succcessfully";
				        if (credited.equals("0") && !debited.equals("0")) {
				        	Assert.assertTrue(true,failureMessageForAssertion);
//				        	etest.log(Status.PASS, successfullLogMessage);
				        } else {
				        	Assert.assertFalse(false);
//				        	etest.log(Status.FAIL, failureMessageForAssertion);
				        }
				    }
		
				    if (customerList.contains(accountType)) {
				    	failureMessageForAssertion = accountType + "Account is not credited";
			        	successfullLogMessage = accountType + "Account is credited succcessfully";
			        	
				        if (!credited.equals("0") && debited.equals("0")) {
				        	Assert.assertTrue(true,failureMessageForAssertion);
//				        	etest.log(Status.PASS, successfullLogMessage);
				        } else {
				        	Assert.assertFalse(false);
//				        	etest.log(Status.FAIL, failureMessageForAssertion);
				        }
				    }
				}
			}
			
			Thread.sleep(5000);
		
//			}catch(StaleElementReferenceException e) {
//				driver.navigate().refresh();
//			}
	}

	
	@Test
	public void deleteReceiptVoucherTest() throws InterruptedException {
		rcp.fillForm("CASH");
		rcp.OKButton.click();
		Thread.sleep(10000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", rcp.receiptList);
//		Point coOfReceiptList = GetCoordinates.getCoordinatesWithinElement(rcp.receiptList, 0.1, 0.1);
		GetCoordinates.getCoordinatesWithinElement(rcp.receiptList, 0.1, 0.1);
		
//		actions.moveToElement(rcp.receiptList, coOfReceiptList.getX(), coOfReceiptList.getY()).click().build().perform();

		rcp.receiptList.click();
		Thread.sleep(5000);
		WebElement lastRow = driver
				.findElement(By.cssSelector("tbody[_ngcontent-hpm-c107] > tr.ng2-smart-row:last-child"));
		// Click the delete button within the last row
		WebElement deleteButton = lastRow.findElement(By.cssSelector(
				"td.ng2-smart-actions > ng2-st-tbody-custom > a.ng2-smart-action-custom-custom.ng-star-inserted > i.fa-solid.fa-trash-can.fa-lg"));
		deleteButton.click();
	}

//	@Test
	public void validationVerificationForReceiptVoucherCreationForm() {
//		List<String> customerList = rcp.getCustomersList();

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView();", rcp.saveButton);
//		Point coForButton = GetCoordinates.getCoordinatesWithinElement(rcp.saveButton, 0.1, 0.1);
//		actions.moveToElement(rcp.saveButton, coForButton.getX(), coForButton.getY()).click().build().perform();

	}
}
