package com.auto.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.auto.pageObjects.LoginPage;

public class TC_LoginDDT_002 extends BaseClass {

	@Test
	public void loginPage() throws IOException, InterruptedException {

		logger.info("Login Method is started");

		LoginPage login = new LoginPage(driver);
		TimeUnit.SECONDS.sleep(40);

		try {
			boolean logWind = login.clickUserIcon();

			if (logWind) {
				logger.info("Entering User name");
				login.checkUserNameAndEnter(userName);

				logger.info("Entering User name");
				login.checkPasswordAndEnter(password);

				logger.info("Click on Login Button");
				login.checkLoginButtonAndClick();

				TimeUnit.SECONDS.sleep(40);

				boolean logVali = login.validatePageAfterLogin();

				Assert.assertEquals(logVali, true);
			}

		} catch (Exception e) {

			logger.info("Exception occured due to" + e.getMessage());
			e.printStackTrace();
		}

	}

}
