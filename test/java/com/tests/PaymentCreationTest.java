package com.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

	String failureMessageForAssertion;
	String successfullLogMessage;
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
	@Test(retryAnalyzer = com.utilities.RetryAnalyzer.class)
	public void paymentVoucherCreationTestOnCashModeOfPayment() throws InterruptedException {
		int totDebit = 0, totCredit = 0;
		List<String> customerList = pcp.getCustomersList();
		String paymentMode = "CASH";
		String vno = pcp.fillForm(paymentMode);
		Assert.assertTrue(pcp.paymentCreatedMessage.isDisplayed(), "Confirmation message is not displayed");
		pcp.searchForPosting(vno);
		
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
		
		for (String[] row : twoDimArray) {
			if(row[0] != null) {
			    String accountType = row[0].trim();
			    String debited = row[1].trim();
			    String credited = row[2].trim();
	
			    if (accountType.equals("CASH A/C")) {
			    	failureMessageForAssertion = accountType + "Cash A/C is not Credited";
		        	successfullLogMessage = accountType + "Account is Credited succcessfully";
			         if (!credited.equals("0") && debited.equals("0")) {
			        	Assert.assertTrue(true,failureMessageForAssertion);
//			        	etest.log(Status.PASS, successfullLogMessage);
			        } else {
			        	Assert.assertFalse(false);
//			        	etest.log(Status.FAIL, failureMessageForAssertion);
			        }
			    }
	
			    if (customerList.contains(accountType)) {
			    	failureMessageForAssertion = accountType + "Account is not debited";
		        	successfullLogMessage = accountType + "Account is Debited succcessfully";
		        	
			        if (credited.equals("0") && !debited.equals("0")) {
			        	Assert.assertTrue(true,failureMessageForAssertion);
//			        	etest.log(Status.PASS, successfullLogMessage);
			        } else {
			        	Assert.assertFalse(false);
//			        	etest.log(Status.FAIL, failureMessageForAssertion);
			        }
			    }
			}
		}
		


	}
	@Test(retryAnalyzer = com.utilities.RetryAnalyzer.class)
	public void paymentVoucherCreationTestOnBankModeOfPayment() throws InterruptedException {
		String paymentMode = "Bank";
		pcp.fillForm(paymentMode);
		Assert.assertTrue(pcp.paymentCreatedMessage.isDisplayed(), "Confirmation message is not displayed");
		int totDebit = 0, totCredit = 0;
		List<String> customerList = pcp.getCustomersList();
		System.out.print(customerList+"customer list----");
		String vno = pcp.fillForm(paymentMode);
		Assert.assertTrue(pcp.paymentCreatedMessage.isDisplayed(), "Confirmation message is not displayed");
		
		
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
		
		pcp.searchForPosting(vno);
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
//			        	etest.log(Status.PASS, successfullLogMessage);
			        } else {
			        	Assert.assertFalse(false);
//			        	etest.log(Status.FAIL, failureMessageForAssertion);
			        }
			    }
	
			    if (customerList.contains(accountType)) {
			    	failureMessageForAssertion = accountType + "Account is not credited";
		        	successfullLogMessage = accountType + "Account is credited succcessfully";
		        	
			        if (!credited.equals("0") && debited.equals("0")) {
			        	Assert.assertTrue(true,failureMessageForAssertion);
//			        	etest.log(Status.PASS, successfullLogMessage);
			        } else {
			        	Assert.assertFalse(false);
//			        	etest.log(Status.FAIL, failureMessageForAssertion);
			        }
			    }
			}
		}
		
		Thread.sleep(5000);
	
	}
}
