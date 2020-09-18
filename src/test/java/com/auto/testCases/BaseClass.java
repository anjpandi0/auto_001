package com.auto.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.auto.utilities.ReadConfig;

public class BaseClass {

	// org.slf4j.Logger logger = LoggerFactory.getLogger(BaseClass.class);

	ReadConfig config = new ReadConfig();

	public String baseURL = config.getAppURL();
	public String userName = config.getUserName();
	public String password = config.getPassword();
	public static WebDriver driver;

	// public static Logger logger = Logger.getLogger(BaseClass.class);
	public static Logger logger = Logger.getLogger(BaseClass.class.getName());

	@BeforeTest
	@Parameters("browser")
	public void setUp(String br) throws InterruptedException {

		// logger = Logger.getLogger("Automation");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", config.getChromePath());
			driver = new ChromeDriver();
		}
		if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", config.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}

	public static String captureScreen() throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String ssPath = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
		File target = new File(ssPath);

		try {
			FileUtils.copyFile(source, target);
			System.out.println("Screen shot taken at" + target);
		} catch (Exception e) {
			System.out.println("Find Screen" + e.getMessage());
		}

		return ssPath;

	}

}
