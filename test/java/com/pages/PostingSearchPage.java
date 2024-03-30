package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostingSearchPage {
public WebDriver driver;
	
	@FindBy(xpath = "//div[@class='category']//input[@id='searchQueryInput']")
	public WebElement searchQueryInput;
	
	@FindBy(xpath = "//div[@id='parentElement1']//input[@type='search']")
    public WebElement searchBox1;

	
	@FindBy(xpath = "//a[contains(text(), 'Search')]")
	public WebElement searchButton;
	
	public PostingSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
