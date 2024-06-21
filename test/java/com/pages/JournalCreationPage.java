package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.GetCoordinates;

public class JournalCreationPage {

	private WebDriver driver;
	
	@FindBy(xpath = "//span[contains(text(), 'Journal')]")
	public WebElement journal;

	@FindBy(xpath = "//span[contains(text(), 'Add Journal')]")
	public WebElement addJournal;
	
	@FindBy(xpath = "//span[contains(text(), 'Vouchers')]")
	public WebElement vouchersElement;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Credit Type')]")
	public WebElement creditType;
	
	@FindBy(xpath = "//span[contains(text(), 'Customer')]")
	public WebElement customerOption;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Debit Type')]")
	public WebElement debitType;

	@FindBy(xpath = "//span[contains(text(), 'Vendor')]")
	public WebElement vendorOption;
	
	@FindBy(className = "mat-datepicker-toggle")
	public WebElement VoucherDatePicker;
	
	@FindBy(className = "mat-calendar-body-cell-content")
	public WebElement date;
	
	@FindBy(xpath = "//span[contains(text(), 'Select Abbreviation')]")
	public WebElement selectAbbreviation;
	

	@FindBy(xpath = "//span[contains(text(), 'COMMON')]")
	public WebElement commonOption;
	
	@FindBy(xpath = "//input[@data-placeholder = 'enter customer name']")
	public WebElement enterCustomerName;
	
	@FindBy(xpath = "//span[contains(text(), 'CUSTOMER1')]")
	public WebElement customer1Option;
	
	@FindBy(xpath = "//input[@data-placeholder = 'enter vendor name']")
	public WebElement enterVendorName;
	
	@FindBy(xpath = "//span[contains(text(), 'SUPPLIER1')]")
	public WebElement supplier1;
	
	@FindBy(xpath = "//input[@data-placeholder = 'Enter amount']")
	public WebElement enterAmount;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement saveButton;
	
	@FindBy(xpath = "//h2[contains(text(),'Payment Saved!')]")
	public WebElement journalCreatedMessage;

	public JournalCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    
	public void refresh() {
		driver.navigate().refresh();
	}
	
	public void fillForm() throws InterruptedException {
		
		creditType.click();
		VoucherDatePicker.click();
		date.click();
		Actions actions = new Actions(driver);

		selectAbbreviation.click();
		commonOption.click();
		
		enterCustomerName.sendKeys("CUSTOMER");
		Thread.sleep(5000);
		customer1Option.click();
		enterVendorName.sendKeys("supplier");
		Thread.sleep(5000);
		supplier1.click();
		enterAmount.sendKeys("5000");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", saveButton);
//		Point coForButton = GetCoordinates.getCoordinatesWithinElement(saveButton, 0.1, 0.1);
		GetCoordinates.getCoordinatesWithinElement(saveButton, 0.1, 0.1);
//		actions.moveToElement(saveButton, coForButton.getX(), coForButton.getY()).click().build().perform();
		Thread.sleep(5000);
		
	}
}
