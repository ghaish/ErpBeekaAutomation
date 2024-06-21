package com.pages;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.base.BaseTest;
import com.utilities.GetCoordinates;

public class ReceiptCreationPage extends BaseTest{

	public WebDriver driver;
	
	@FindBy(xpath = "//h2[contains(text(),'Receipt')]")
	public WebElement receiptTitle;
	
	@FindBy(xpath = "//h2[contains(text(),'RECEIPT DETAILS')]")
	public WebElement receiptDetailsTitle;
	
	@FindBy(className = "mat-datepicker-toggle")
	public WebElement VoucherDatePicker;

//	@FindBy(className = "mat-calendar-body-cell-content")
//	public WebElement date;
	
//	@FindBy(xpath = "//div[@className='mat-calendar-body-cell-content' and text()='17']")
//	public WebElement date;
	
	@FindBy(xpath = "//div[contains(text(), '8')]")
	public WebElement date;

	@FindBy(id = "mat-select-8")
	public WebElement modeOfPayment;
	
	@FindBy(id = "mat-select-0")
	public WebElement modeOfPaymentLabel;
	
	@FindBy(id = "mat-option-4")
	public WebElement bankOption;
	
	@FindBy(id = "mat-select-6")
	public WebElement payeeType;

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

//	@FindBy(xpath = "//mat-label[contains(text(),'Mode of Payment')]")
//	public WebElement modeofPaymentLabel;

//	@FindBy(xpath = "//span[contains(text(), 'Type')]")
//	public WebElement payeeType;

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
	
//	@FindBy(xpath = "//span[contains(text(), 'Bank')]")
//	public WebElement bankOption;

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
	
	@FindBy(id = "mat-select-value-3")
	public WebElement abbrevation;
	
	@FindBy(id = "mat-option-37")
	public WebElement abbrevation_Option;

	@FindBy(xpath = "//span[contains(text(), 'CUSTOMER')]")
	public WebElement customerOption;
	
	@FindBy(xpath = "//textarea[@data-placeholder = 'Enter Customer Name']")
	public WebElement enterCustomerName;
	
	@FindBy(xpath = "//span[contains(text(), 'CUSTOMER1')]")
	public WebElement customer1Option;
	
	@FindBy(xpath = "//input[@data-placeholder = 'Enter Amount']")
	public WebElement enterAmount;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement saveButton;
	
	@FindBy(xpath = "//h2[contains(text(),'Receipt created!')]")
	public WebElement receiptCreatedMessage;
	
	@FindBy(xpath = "//button[contains(text(),'OK')]")
	public WebElement OKButton;
	
	@FindBy(xpath = "//button[contains(text(),'No')]")
	public WebElement NOButton;
	
	@FindBy(xpath = "//h2[contains(text(),'Do you want to print the receipt?')]")
	public WebElement printtReceiptTitle;

	public ReceiptCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public String fillForm(String modeOfPayment) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		VoucherDatePicker.click();
		date.click();
		Actions actions = new Actions(driver);
		Thread.sleep(5000);
		
//		Point coForModeOfPayment = GetCoordinates.getCoordinatesWithinElement(modeofPaymentLabel, 0.5, 0.5);
//	    GetCoordinates.getCoordinatesWithinElement(modeofPaymentLabel, 0.5, 0.5);
//		wait.until(ExpectedConditions.elementToBeClickable(modeofPaymentLabel));
//	    actions.moveToElement(modeofPaymentLabel, modeofPaymentLabel.getX(), coForModeOfPayment.getY()).click().build().perform();
//		driver.findElement(By.xpath("//mat-label[contains(text(),'Mode of Payment')]")).click();
		modeOfPaymentLabel.click();
		if(modeOfPayment.equals("CASH")){
			System.out.println("searching cashoption");
			cashOption.click();
			System.out.println("cashoption found");
//			cashLedger.sendKeys("f");
			wait.until(ExpectedConditions.elementToBeClickable(cashLedger));
			cashLedger.sendKeys("e");
			Thread.sleep(2000);
			actions.sendKeys(cashLedger, Keys.BACK_SPACE).perform();
			WebElement autocompletePanel = driver.findElement(By.cssSelector("div.mat-autocomplete-panel"));
			autocompletePanel.click();
			
		}
		else if(modeOfPayment.equals("Bank")) {
			bankOption.click();
//			driver.findElement(By.xpath("//span[contains(text(),'Bank')]")).click();
			toBank.click();
			toBank.sendKeys("e");
			Thread.sleep(2000);
			actions.sendKeys(toBank, Keys.BACK_SPACE).perform();
			WebElement autocompletePanel = driver.findElement(By.cssSelector("div.mat-autocomplete-panel"));
			autocompletePanel.click();
		}
//		cashACOption.click();
		Thread.sleep(5000);

//		cashACOption.click();
//		selectCurrency.click();

		GetCoordinates.getCoordinatesWithinElement(abbrevation, 0.1, 0.1);
		Thread.sleep(5000);
		
		GetCoordinates.getCoordinatesWithinElement(abbrevation_Option, 0.1, 0.1);
		
		GetCoordinates.getCoordinatesWithinElement(selectCurrency, 0.5, 0.5);
		rupeeOption.click();
		WebElement voucherNoelement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//p[contains(@style, 'color: #45aedd') and contains(@style, 'font-weight: 500') and contains(@style, 'display: inline') and contains(@style, 'margin-right: 35%')]")
            ));
        String voucherNo = voucherNoelement.getText();
        System.out.println("VOUCHER no:------ ");
        System.out.println(voucherNo);
		System.out.println("before scroll--");
		js.executeScript("arguments[0].scrollIntoView();", payeeType);
		System.out.println("after scrollingn--");
		
//		Point coForType = GetCoordinates.getCoordinatesWithinElement(payeeType, 0.5, 0.5);
//		GetCoordinates.getCoordinatesWithinElement(payeeType, 0.5, 0.5);
//		actions.moveToElement(payeeType, coForType.getX(), coForType.getY()).click().build().perform();
//		payeeType.click();
//		GetCoordinates.getCoordinatesWithinElement(payeeType, 0.5, 0.5);
//		wait.until(ExpectedConditions.elementToBeClickable(payeeType)).click();
//		refresh();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[contains(text(),'Type')]")).click();
//		driver.findElement(By.xpath("//span[contains(text(),'Type')]")).click();

		System.out.println("action click-- FILLING THE FORM.....");
		customerOption.click();
		Thread.sleep(3000);
		enterCustomerName.sendKeys("e");
		Thread.sleep(2000);
		actions.sendKeys(enterCustomerName, Keys.BACK_SPACE).perform();
		WebElement autocompletePanel = driver.findElement(By.cssSelector("div.mat-autocomplete-panel"));
		autocompletePanel.click();
		Thread.sleep(5000);
		enterAmount.sendKeys("5005");
		
		js.executeScript("arguments[0].scrollIntoView();", saveButton);
//		Point coForButton = GetCoordinates.getCoordinatesWithinElement(saveButton, 0.1, 0.1);
		GetCoordinates.getCoordinatesWithinElement(saveButton, 0.1, 0.1);
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		
		driver.findElements(By.xpath("//button[contains(text(),'No')]")).get(1).click();
		
//		actions.moveToElement(saveButton, coForButton.getX(), coForButton.getY()).click().build().perform();
		Thread.sleep(5000);
		return voucherNo;
		
	}
	public List<String> getCustomersList() throws InterruptedException {
		Actions actions = new Actions(driver);
		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", payeeType);
		System.out.println("get customer listt......");
//		GetCoordinates.getCoordinatesWithinElement(payeeType, 0.5, 0.5);
		refresh();
		driver.findElement(By.xpath("//span[contains(text(),'Type')]")).click();
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
	
	public String getLastCreatedVoucherNo() throws InterruptedException {
		ReceiptListPage rlp = new ReceiptListPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		OKButton.click();
		
		js.executeScript("arguments[0].scrollIntoView();", receiptTitle);
		Thread.sleep(6000);
		receiptList.click();
		driver.findElements(By.cssSelector("button.mat-icon-button[aria-label='Open calendar']")).get(0).click();;
		
//		rlp.fromDateInput.click();
//		rlp.fromdDateInputValue.click();
		driver.findElement(By.xpath("//div[contains(text(), '1')]")).click();
		driver.findElements(By.cssSelector("button.mat-icon-button[aria-label='Open calendar']")).get(1).click();;
		driver.findElement(By.xpath("//div[contains(text(), '30')]")).click();
		driver.findElement(By.xpath("//a[contains(text(), 'Search')]")).click();
		js.executeScript("arguments[0].scrollIntoView();", rlp.lastPage);
		GetCoordinates.getCoordinatesWithinElement(rlp.lastPage, 0.1, 0.1);
		
		WebElement table = driver.findElement(By.cssSelector(".table"));

		WebElement lastRow = table.findElement(By.cssSelector("tbody tr:last-child"));

		// Find the last column in the last row (voucher number column)
		WebElement lastColumn = lastRow.findElement(By.cssSelector("td:first-child"));

		// Get the text of the last column (voucher number value)
		String voucherNumber = lastColumn.getText();
		return voucherNumber;

	}
	public void clickMasterTab() {
		
		GetCoordinates.getCoordinatesWithinElement(masterTab, 0.5, 0.5);	
	}
	
	public void searchForPosting(String lastCreatedVoucherNo) throws InterruptedException {
		ReceiptListPage rlp = new ReceiptListPage(driver);
		PostingSearchPage psp = new PostingSearchPage(driver);
		clickMasterTab();
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
