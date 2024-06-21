package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseTest;

public class VouchersSidebar extends BaseTest {
	public WebDriver driver;
	@FindBy(xpath = "//span[contains(text(), 'Receipt')]")
	public WebElement receipt;
	
	@FindBy(xpath = "//span[contains(text(), 'Add Receipt')]")
	public WebElement addReceipt;
	
	@FindBy(xpath = "//span[contains(text(), 'Receipt List')]")
	public WebElement receiptList;
	
	@FindBy(xpath = "//span[contains(text(), 'Payment')]")
	public WebElement payment;
	
	@FindBy(xpath = "//span[contains(text(), 'Add Payment')]")
	public WebElement addPayment;
	
	@FindBy(xpath = "//span[contains(text(), 'Payment List')]")
	public WebElement paymentList;
	
	@FindBy(xpath = "//span[contains(text(), 'Journal')]")
	public WebElement journal;
	
	@FindBy(xpath = "//span[contains(text(), 'Add Journal')]")
	public WebElement addJournal;
	
	@FindBy(xpath = "//span[contains(text(), 'Journal List')]")
	public WebElement journalList;
	
	@FindBy(xpath = "//span[contains(text(), 'Compound Journal')]")
	public WebElement compoundJournal;
	
	@FindBy(xpath = "//span[contains(text(), 'Add Compound Journal')]")
	public WebElement addCompoundJournal;
	
	@FindBy(xpath = "//span[contains(text(), 'Compound Journal List')]")
	public WebElement compoundJournalList;
	
	@FindBy(xpath = "//span[contains(text(), 'Purchase')]")
	public WebElement Purchase;
	
	@FindBy(xpath = "//span[contains(text(), 'Add Purchase')]")
	public WebElement addPurchase;
	
	@FindBy(xpath = "//span[contains(text(), 'Purchase List')]")
	public WebElement purchaseList;
	
	@FindBy(xpath = "//span[contains(text(), 'Add Purchase Return')]")
	public WebElement addPurchaseReturn;
	
	@FindBy(xpath = "//span[contains(text(), 'Purchase Return List')]")
	public WebElement purchaseReturnList;
	
	@FindBy(xpath = "//span[contains(text(), 'Sales')]")
	public WebElement Sales;
	
	@FindBy(xpath = "//span[contains(text(), 'Debit Note')]")
	public WebElement debitNote;
	
	@FindBy(xpath = "//span[contains(text(), 'Credit Note')]")
	public WebElement creditNote;
	
	@FindBy(xpath = "//span[contains(text(), 'Contra')]")
	public WebElement contra;
	
	@FindBy(xpath = "//span[contains(text(), 'Select Abbreviation')]")
	public WebElement selectAbbreviation;
	
	@FindBy(xpath = "//span[contains(text(), 'Master')]")
	public WebElement masterTab;
	
	@FindBy(xpath = "//span[contains(text(), 'Vouchers')]")
	public WebElement vouchersTab;
	
	public VouchersSidebar(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
}
