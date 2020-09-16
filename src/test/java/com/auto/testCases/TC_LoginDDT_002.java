package com.auto.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.auto.pageObjects.LoginPage;
import com.auto.utilities.GenericUtilities;

public class TC_LoginDDT_002 extends BaseClass {

	Logger logger = LoggerFactory.getLogger(GenericUtilities.class);

	@Test
	public void loginPage() throws IOException, InterruptedException {

		logger.info("Login Method is started");

		LoginPage login = new LoginPage(driver);
		TimeUnit.SECONDS.sleep(40);

		try {
			// boolean logWind = getLoginPage().validateLoginPopup(driver);

			if (login.getUserName().isDisplayed()) {
				logger.info("Entering User name");
				login.checkUserNameAndEnter(userName);

				logger.info("Entering User name");
				login.checkPasswordAndEnter(password);

				logger.info("Click on Login Button");
				login.checkLoginButtonAndClick();

				Thread.sleep(2000);

				boolean logVali = login.validatePageAfterLogin();

				Assert.assertEquals(logVali, true);
			}

		} catch (Exception e) {

			logger.info("Exception occured due to" + e.getMessage());
			e.printStackTrace();
		}

	}

}
