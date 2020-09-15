package com.auto.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver1) {
		driver = driver1;
		PageFactory.initElements(driver, this);

	}

	@FindBy(name = "email")
	@CacheLookup
	WebElement userName;

	@FindBy(name = "pass")
	@CacheLookup
	WebElement password;

	@FindBy(name = "login")
	@CacheLookup
	WebElement btnLogin;

	public void setUserName(String user) {
		userName.sendKeys(user);
	}

	public void setPassword(String pass) {
		password.sendKeys(pass);
	}

	public void setBtnLogin() {
		btnLogin.click();
	}

}
