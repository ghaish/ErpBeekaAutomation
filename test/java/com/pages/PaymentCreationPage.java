package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.GetCoordinates;

public class PaymentCreationPage {
public WebDriver driver;
	
	@FindBy(className = "mat-datepicker-toggle")
	public WebElement VoucherDatePicker;

	@FindBy(className = "mat-calendar-body-cell-content")
	public WebElement date;

	@FindBy(id = "mat-select-8")
	public WebElement modeOfPayment;

	@FindBy(xpath = "//span[contains(text(), 'Vouchers')]")
	public WebElement vouchersElement;

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

	@FindBy(xpath = "//span[contains(text(), 'Payment')]")
	public WebElement payment;

	@FindBy(xpath = "//span[contains(text(), 'Add Payment')]")
	public WebElement addPayment;

	@FindBy(xpath = "//span[contains(text(), 'Cash')]")
	public WebElement cashOption;

	@FindBy(xpath = "//input[@data-placeholder = 'Cash Ledger']")
	public WebElement cashLedger;
	
	@FindBy(xpath = "//span[contains(text(), 'CASH A/C')]")
	public WebElement cashACOption;
	
	@FindBy(xpath = "//span[contains(text(), 'Select Currency')]")
	public WebElement selectCurrency;
	
	@FindBy(xpath = "//span[contains(text(), ' INDIAN RUPEE (INR) ')]")
	public WebElement rupeeOption;

	@FindBy(xpath = "//span[contains(text(), 'Customer')]")
	public WebElement customerOption;
	
	@FindBy(xpath = "//input[@data-placeholder = 'enter customer name']")
	public WebElement enterCustomerName;
	
	@FindBy(xpath = "//span[contains(text(), 'CUSTOMER1')]")
	public WebElement customer1Option;
	
	@FindBy(xpath = "//input[@data-placeholder = 'Enter amount']")
	public WebElement enterAmount;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement saveButton;
	
	@FindBy(xpath = "//h2[contains(text(),'Payment Saved!')]")
	public WebElement paymentCreatedMessage;

	public PaymentCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void fillForm() throws InterruptedException {
		VoucherDatePicker.click();
		date.click();
		Actions actions = new Actions(driver);
		Point coForModeOfPayment = GetCoordinates.getCoordinatesWithinElement(modeofPaymentLabel, 0.1, 0.1);
		actions.moveToElement(modeofPaymentLabel, coForModeOfPayment.getX(), coForModeOfPayment.getY()).click().build().perform();
		cashOption.click();
		
		cashLedger.sendKeys("cash");
		Thread.sleep(5000);

		cashACOption.click();
		selectCurrency.click();
		rupeeOption.click();
		Point coForType = GetCoordinates.getCoordinatesWithinElement(payeeType, 0.1, 0.1);
		actions.moveToElement(payeeType, coForType.getX(), coForType.getY()).click().build().perform();
		
		customerOption.click();
		enterCustomerName.sendKeys("CUSTOMER");
		Thread.sleep(5000);
		customer1Option.click();
		Thread.sleep(5000);
		enterAmount.sendKeys("5000");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", saveButton);
		Point coForButton = GetCoordinates.getCoordinatesWithinElement(saveButton, 0.1, 0.1);
		actions.moveToElement(saveButton, coForButton.getX(), coForButton.getY()).click().build().perform();
		Thread.sleep(5000);
		
	}

	public void refresh() {
		driver.navigate().refresh();
	}

}

