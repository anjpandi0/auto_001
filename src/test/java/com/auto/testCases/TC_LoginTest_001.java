package com.auto.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.auto.pageObjects.LoginPage;

import junit.framework.Assert;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginPage() throws IOException {

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		try {
			logger.info("Page is opening");
			Thread.sleep(10000);
			LoginPage log = new LoginPage(driver);

			log.setUserName(userName);
			logger.info("User Nae entered");

			log.setPassword(password);
			logger.info("password entered");

			log.setBtnLogin();
			logger.info("clicked on login");

			Thread.sleep(100000);

			if(driver.findElement(By.xpath("//u[contains(text(),'Facebook')]")).isDisplayed()) {
				Assert.assertTrue("Test case Passed",true);
				captureScreen(driver, "TC_LoginTest_001");

			}
			else {
				captureScreen(driver, "TC_LoginTest_001-->loginPage");
				Assert.assertTrue("Test case Passed",false);

			}
			
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
