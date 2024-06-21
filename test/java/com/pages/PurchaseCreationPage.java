package com.pages;

import javax.security.auth.Refreshable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseTest;
import com.utilities.GetCoordinates;

public class PurchaseCreationPage extends BaseTest{
	public WebDriver driver;
	
//	@FindBy(xpath = "//input[@data-placeholder = 'Select create date']")
//	public WebElement selectCreateDate;
	
//	@FindBy(id = "mat-input-36")
//	public WebElement selectCreateDate;
	
	
	
	@FindBy(xpath = "//input[@id='mat-input-36']")
	private WebElement selectCreateDate;
	
	@FindBy(xpath = "//div[contains(text(), '8')]")
	public WebElement date;
	
	@FindBy(xpath = "//input[@data-placeholder = 'Select Voucher Date']")
	public WebElement selectVoucherDate;
	
	@FindBy(xpath = "//input[@data-placeholder = 'Enter Vendor Name']")
	public WebElement enterVendorName;
	
	@FindBy(xpath = "//span[contains(text(), 'Select Godown')]")
	public WebElement selectGodown;
	
	@FindBy(xpath = "//textarea[@data-placeholder = 'Enter Item Name']")
	public WebElement enterItemName;
	
	@FindBy(id = "qty")
	public WebElement qty;
	
	@FindBy(id = "rate")
	public WebElement rate;
	
	@FindBy(id = "mat-select-14")
	public WebElement unit;
	
	@FindBy(id = "mat-select-17")
	public WebElement rack;
	
	public PurchaseCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void fillForm() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);
		Thread.sleep(3000);
		driver.navigate().refresh();
		GetCoordinates.getCoordinatesWithinElement(selectCreateDate, 0.5, 0.5);
//		driver.findElement(By.xpath("//input[@data-placeholder = 'Select create date']")).click();
//		selectCreateDate.click();
		date.click();
		selectVoucherDate.click();
		date.click();
		enterVendorName.sendKeys("e");
		Thread.sleep(2000);
		actions.sendKeys(enterVendorName, Keys.BACK_SPACE).perform();
		WebElement autocompletePanel = driver.findElement(By.cssSelector("div.mat-autocomplete-panel"));
		autocompletePanel.click();
		selectGodown.click();
		js.executeScript("arguments[0].scrollIntoView();", enterItemName);
		qty.sendKeys("2");
		rate.sendKeys("10");
		
	}
}

