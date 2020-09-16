package com.auto.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auto.utilities.GenericUtilities;

public class LoginPage extends GenericUtilities {

	Logger logger = LoggerFactory.getLogger(GenericUtilities.class);

	WebDriver driver;

	public LoginPage(WebDriver driver1) {
		driver = driver1;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@class='_2zrpKA _1dBPDZ']")
	@CacheLookup
	WebElement userName;

	@FindBy(xpath = "//input[@type='password']")
	@CacheLookup
	WebElement password;

	@FindBy(xpath = ".//a[.='Login']")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(xpath = ".//span//span[.='Login']")
	@CacheLookup
	WebElement loginWind;

	@FindBy(xpath = "(//button[@type='submit'])[2]")
	@CacheLookup
	WebElement loginSubmit;

	public void checkUserNameAndEnter(String user) {
		explicitWaitVisibility(driver, userName);
		if (userName.isDisplayed()) {
			enterText(userName, user);
		} else {
			logger.info("User name is not available");
		}

	}

	public void checkPasswordAndEnter(String pass) {
		explicitWaitVisibility(driver, password);
		if (password.isDisplayed()) {
			enterText(password, pass);
		} else {
			logger.info("password is not available");
		}

	}

	public void checkLoginButtonAndClick() throws Exception {
		explicitWaitVisibility(driver, loginSubmit);
		if (loginSubmit.isEnabled()) {
			click(loginSubmit);
		} else {
			logger.info("password is not available");
		}

	}

	public boolean validateLoginPopup(WebDriver driver) throws Exception {
		boolean flag = false;
		pageLoadTime30Seconds(driver);
		try {
			explicitWaitVisibility(driver, loginWind);
			if (loginWind.isEnabled()) {
				flag = true;

			} else {
				logger.info("loginWind is not available");

			}
		} catch (NullPointerException e) {
			explicitWaitVisibility(driver, btnLogin);
			click(btnLogin);
			flag = true;
		}
		return flag;

	}

	public Logger getLogger() {
		return logger;
	}

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

	public WebElement getLoginWind() {
		return loginWind;
	}

	public WebElement getLoginSubmit() {
		return loginSubmit;
	}

}
