package com.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.GetCoordinates;

public class ReceiptListPage {

	public WebDriver driver;
	
	@FindBy(xpath = "//a[@aria-label = 'Last']")
	public WebElement lastPage;
	
	public ReceiptListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

}
