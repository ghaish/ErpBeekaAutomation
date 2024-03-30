package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseTest;
import com.utilities.GetCoordinates;

public class ReceiptCreationPage extends BaseTest{

	public WebDriver driver;
	ReceiptListPage rlp = new ReceiptListPage(driver);
	PostingSearchPage psp = new PostingSearchPage(driver);
	@FindBy(xpath = "//h2[contains(text(),'Receipt')]")
	public WebElement receiptTitle;
	
	@FindBy(className = "mat-datepicker-toggle")
	public WebElement VoucherDatePicker;

	@FindBy(className = "mat-calendar-body-cell-content")
	public WebElement date;

	@FindBy(id = "mat-select-8")
	public WebElement modeOfPayment;

	@FindBy(xpath = "//span[contains(text(), 'Vouchers')]")
	public WebElement vouchersElement;
	
	@FindBy(xpath = "//span[contains(text(), 'Master')]")
	public WebElement masterTab;

	@FindBy(xpath = "//span[contains(text(), 'Select Abbreviation')]")
	public WebElement selectAbbreviation;

	@FindBy(xpath = "//span[contains(text(), 'Select Currency')]")
	public WebElement selectCurrElement;

	@FindBy(xpath = "//mat-label[contains(text(),'Bill No')]")
	public WebElement billNoLabel;

	@FindBy(xpath = "//mat-label[contains(text(),'Mode of Payment')]")
	public WebElement modeofPaymentLabel;

	@FindBy(xpath = "//span[contains(text(), 'Type')]")
	public WebElement payeeType;

	@FindBy(xpath = "//span[contains(text(), 'Receipt')]")
	public WebElement receipt;
	
	@FindBy(xpath = "//span[contains(text(), 'Posting')]")
	public WebElement posting;
	
//	@FindBy(xpath = "//span[contains(text(), 'Posting')]/ancestor::div[@class='sub_link_name']")
//    public WebElement postingSubMenu;
	@FindBy(linkText = "Posting")
    public WebElement postingSubMenu;

	
	@FindBy(xpath = "//span[contains(text(), 'Add Receipt')]")
	public WebElement addReceipt;
	
	@FindBy(xpath = "//span[contains(text(), 'Receipt List')]")
	public WebElement receiptList;

	@FindBy(xpath = "//span[contains(text(), 'CASH')]")
	public WebElement cashOption;
	
	@FindBy(xpath = "//span[contains(text(), 'Bank')]")
	public WebElement bankOption;

	@FindBy(xpath = "//input[@data-placeholder = 'Cash Ledger']")
	public WebElement cashLedger;
	
	@FindBy(xpath = "//input[@data-placeholder = 'To Bank']")
	public WebElement toBank;
	
	@FindBy(xpath = "//span[contains(text(), 'CASH A/C')]")
	public WebElement cashACOption;
	
	@FindBy(xpath = "//span[contains(text(), 'Select Currency')]")
	public WebElement selectCurrency;
	
	@FindBy(xpath = "//span[contains(text(), ' INDIAN RUPEE (INR) ')]")
	public WebElement rupeeOption;

	@FindBy(xpath = "//span[contains(text(), 'CUSTOMER')]")
	public WebElement customerOption;
	
	@FindBy(xpath = "//input[@data-placeholder = 'enter customer name']")
	public WebElement enterCustomerName;
	
	@FindBy(xpath = "//span[contains(text(), 'CUSTOMER1')]")
	public WebElement customer1Option;
	
	@FindBy(xpath = "//input[@data-placeholder = 'Enter amount']")
	public WebElement enterAmount;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement saveButton;
	
	@FindBy(xpath = "//h2[contains(text(),'Receipt created!')]")
	public WebElement receiptCreatedMessage;
	
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement OKButton;

	public ReceiptCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void fillForm(String modeOfPayment) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		VoucherDatePicker.click();
		date.click();
		Actions actions = new Actions(driver);
		Point coForModeOfPayment = GetCoordinates.getCoordinatesWithinElement(modeofPaymentLabel, 0.1, 0.1);
		actions.moveToElement(modeofPaymentLabel, coForModeOfPayment.getX(), coForModeOfPayment.getY()).click().build().perform();
		if(modeOfPayment.equals("CASH")){
			cashOption.click();
//			cashLedger.sendKeys("f");
			cashLedger.sendKeys("e");
			Thread.sleep(2000);
			actions.sendKeys(cashLedger, Keys.BACK_SPACE).perform();
			WebElement autocompletePanel = driver.findElement(By.cssSelector("div.mat-autocomplete-panel"));
			autocompletePanel.click();
			Thread.sleep(5000);
		}
		else if(modeOfPayment.equals("Bank")) {
			bankOption.click();
			toBank.sendKeys("e");
			Thread.sleep(2000);
			actions.sendKeys(toBank, Keys.BACK_SPACE).perform();
			WebElement autocompletePanel = driver.findElement(By.cssSelector("div.mat-autocomplete-panel"));
			autocompletePanel.click();
			Thread.sleep(5000);
		}
//		cashACOption.click();
		Thread.sleep(5000);

//		cashACOption.click();
		selectCurrency.click();
		rupeeOption.click();
		System.out.println("before scroll--");
		js.executeScript("arguments[0].scrollIntoView();", payeeType);
		System.out.println("after scrollingn--");
		
		Point coForType = GetCoordinates.getCoordinatesWithinElement(payeeType, 0.1, 0.1);
		actions.moveToElement(payeeType, coForType.getX(), coForType.getY()).click().build().perform();
		System.out.println("action click-");
		customerOption.click();
		enterCustomerName.sendKeys("e");
		Thread.sleep(2000);
		actions.sendKeys(cashLedger, Keys.BACK_SPACE).perform();
		WebElement autocompletePanel = driver.findElement(By.cssSelector("div.mat-autocomplete-panel"));
		autocompletePanel.click();
		Thread.sleep(5000);
		enterAmount.sendKeys("5005");
		
		js.executeScript("arguments[0].scrollIntoView();", saveButton);
		Point coForButton = GetCoordinates.getCoordinatesWithinElement(saveButton, 0.1, 0.1);
		actions.moveToElement(saveButton, coForButton.getX(), coForButton.getY()).click().build().perform();
		Thread.sleep(5000);
		
	}
	public List<String> getCustomersList() throws InterruptedException {
		
		vouchersElement.click();
		receipt.click();
		Thread.sleep(3000);
		addReceipt.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", payeeType);
		Actions actions = new Actions(driver);
		Point coForType = GetCoordinates.getCoordinatesWithinElement(payeeType, 0.1, 0.1);
		actions.moveToElement(payeeType, coForType.getX(), coForType.getY()).click().build().perform();
		customerOption.click();
		enterCustomerName.sendKeys("e");
		Thread.sleep(2000);
		actions.sendKeys(enterCustomerName, Keys.BACK_SPACE).perform();
		WebElement autocompletePanel = driver.findElement(By.cssSelector("div.mat-autocomplete-panel"));
		List<WebElement> matOptions = autocompletePanel.findElements(By.tagName("mat-option"));

		// Iterate through the list of mat-option elements and retrieve their text content
		List<String> customerOptions = new ArrayList<>();
		for (WebElement matOption : matOptions) {
		    String text = matOption.findElement(By.className("mat-option-text")).getText().trim();
		    customerOptions.add(text);
		}

		// Now, optionTexts list contains the text content of all mat-option elements
		System.out.println("option texts------"+customerOptions);
		js.executeScript("arguments[0].scrollIntoView();", receiptTitle);
		refresh();
		return customerOptions;
	}
	
	public String getLastCreatedVoucherNo() {
		Actions actions = new Actions(driver);
		ReceiptListPage rlp = new ReceiptListPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		OKButton.click();
		
		js.executeScript("arguments[0].scrollIntoView();", receiptTitle);
		receiptList.click();
		js.executeScript("arguments[0].scrollIntoView();", rlp.lastPage);
		Point coForLastPageButton = GetCoordinates.getCoordinatesWithinElement(rlp.lastPage, 0.1, 0.1);
		actions.moveToElement(rlp.lastPage, coForLastPageButton.getX(), coForLastPageButton.getY()).click().build()
				.perform();
		WebElement table = driver.findElement(By.cssSelector(".table"));

		WebElement lastRow = table.findElement(By.cssSelector("tbody tr:last-child"));

		// Find the last column in the last row (voucher number column)
		WebElement lastColumn = lastRow.findElement(By.cssSelector("td:first-child"));

		// Get the text of the last column (voucher number value)
		String voucherNumber = lastColumn.getText();
		return voucherNumber;

	}
	public void clickMasterTab() {
		Actions actions = new Actions(driver);
		Point coForMasterTab = GetCoordinates.getCoordinatesWithinElement(masterTab, 0.1, 0.1);
		actions.moveToElement(masterTab, coForMasterTab.getX(), coForMasterTab.getY()).click().build().perform();
		
	}
	
	public void searchForPosting(String lastCreatedVoucherNo) throws InterruptedException {
		clickMasterTab();
		posting.click();
		postingSubMenu.click();
		Thread.sleep(5000);
		psp.searchQueryInput.click();
		psp.searchQueryInput.clear();
//		psp.searchQueryInput.sendKeys(voucherNumber);
		psp.searchQueryInput.sendKeys(lastCreatedVoucherNo);
		// Print the voucher number
		psp.searchButton.click();
	}
	public void refresh() {
		driver.navigate().refresh();
	}

}
