package com.auto.testCases;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.auto.pageObjects.LoginPage;
import com.auto.utilities.GenericUtilities;
import com.auto.utilities.ReadConfig;

public class BaseClass {

	org.slf4j.Logger logger = LoggerFactory.getLogger(BaseClass.class);

	ReadConfig config = new ReadConfig();

	public String baseURL = config.getAppURL();
	public String userName = config.getUserName();
	public String password = config.getPassword();
	public static WebDriver driver;

	// public static Logger logger;

	@BeforeClass
	@Parameters("browser")
	public void setUp(String br) throws InterruptedException {

		logger.info("Automation");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", config.getChromePath());
			driver = new ChromeDriver();
		}
		if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", config.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(baseURL);
	//	driver.manage().deleteAllCookies();

		Thread.sleep(2000);
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screen shot taken at" + target);

	}

}
