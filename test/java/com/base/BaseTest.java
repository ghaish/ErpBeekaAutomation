package com.base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static FileReader fr;
	public static WebDriverWait wait;
	public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public JavascriptExecutor js;
	@BeforeClass
	public void setup() throws IOException {
		if (driver == null) {
			fr = new FileReader("D:\\BeekaERP\\src\\test\\resources\\configfiles\\config.properties");
			prop.load(fr);
		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(prop.getProperty("url"));
		}
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"\\test-output\\testReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.config().setOfflineMode(true);
        sparkReporter.config().setDocumentTitle("Simple Automation Report");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setEncoding("UTF-8");
        
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		js = (JavascriptExecutor) driver;
	}

	@AfterClass
	public void teardown() {
		driver.close();
	}
}
