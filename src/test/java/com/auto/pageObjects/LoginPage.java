package com.auto.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.auto.utilities.GenericUtilities;

public class LoginPage extends GenericUtilities {

	WebDriver driver;

	public LoginPage(WebDriver driver1) {
		driver = driver1;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//a[@class='userlink']")
	@CacheLookup
	WebElement userNameIcon;

	@FindBy(xpath = ".//a[@class='ico-login']")
	@CacheLookup
	WebElement iconLogin;

	@FindBy(id = "Username")
	@CacheLookup
	WebElement userName;

	@FindBy(id = "Password")
	@CacheLookup
	WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	@CacheLookup
	WebElement loginSubmit;

	@FindBy(xpath = "//a[@class='ico-account']")
	@CacheLookup
	WebElement validateAfterLogin;
	
	@FindBy(xpath = ".//li[@class='inbox']")
	@CacheLookup
	WebElement inboxIcon;

	public boolean clickUserIcon() throws Exception {
		boolean flag = false;
		explicitWaitVisibility(driver, userNameIcon);
		click(userNameIcon);

		explicitWaitVisibility(driver, iconLogin);
		if (iconLogin.isDisplayed()) {
			click(iconLogin);
			flag = true;
		}
		return flag;

	}

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

	public boolean validatePageAfterLogin() throws Exception {
		boolean flag = false;
		explicitWaitVisibility(driver, inboxIcon);
		if (inboxIcon.isDisplayed()) {
			flag = true;
		}
		return flag;

	}
}
