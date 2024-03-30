package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;
	
	@FindBy(id = "mat-input-0")
	private WebElement email;
	@FindBy(id = "mat-input-1")
	private WebElement password;
	@FindBy(className = "button")
	private WebElement continueButton;
	@FindBy(className = "dropdown-toggle")
	private WebElement dropdown_toggle;
	@FindBy(partialLinkText = "Logout")
    private WebElement logout;
	@FindBy(xpath = "//label[contains(text(), 'Invalid email or password.')]")
    private WebElement invalidEmailOrPasswordLabel;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterMail(String uname, String pword) throws InterruptedException {
		email.sendKeys(uname);
		password.sendKeys(pword);
		continueButton.click();
//		driver.findElement(By.id("mat-input-0")).sendKeys(uname);
//		driver.findElement(By.id("mat-input-1")).sendKeys(pword);
//		driver.findElement(By.className("button")).click();
		
	}
	public void logout(){
		dropdown_toggle.click();
		logout.click();
	}
	public void clear() {
		email.clear();
		password.clear();
	}
}
