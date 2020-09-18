package com.auto.utilities;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericUtilities {

	WebDriver driver;

	public static Logger logger = Logger.getLogger(GenericUtilities.class);

	public void click(WebElement element) throws Exception {
		// @vtiruvee --06/05/2018 added loop to click on the given element\
		for (int m = 0; m < 10; m++) {
			if (element != null) {
				element.click();
				break;
			}
		}
	}

	public void enterText(WebElement element, String Val) {
		element.clear();
		element.sendKeys(Val);
	}

	public void implecitWait(WebDriver driver, int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

	public void selectText(WebElement element, String selectType, String val) {

		switch (selectType) {
		case "selectByVisibleText":
			new Select(element).selectByVisibleText(val);
			break;
		case "selectByValue":
			new Select(element).selectByValue(val);
			break;
		case "selectByIndex":
			new Select(element).selectByIndex(Integer.parseInt(val));
			break;
		}

	}

	public void explicitWaitClickable(WebDriver driver, WebElement element) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		if (element != null)
			wait.until(ExpectedConditions.elementToBeClickable(element));
		else
			throw new Exception("Element is null");

	}

	public void udfWaitUntillSpinOverlayDisappears(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 180);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath(".//div[@class='spinner']")).size() > 0) {
			for (int i = 0; i < driver.findElements(By.xpath(".//div[@class='spinner']")).size(); i++) {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//div[@class='spinner']")));
			}
		}

	}

	public boolean elementExists(WebElement element) {

		boolean blnDisplay = false;
		try {
			if (element != null)
				blnDisplay = true;
		} catch (Exception e1) {
		}
		return blnDisplay;

	}

	public void pageLoadTime45Seconds(WebDriver driver) throws Exception {
		if (!checkIfcssSelectorExists(driver, "backdrop")) {
			(new WebDriverWait(driver, 45, 200))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("backdrop")));
		} else if (!checkIfXpathExists(driver, ".//*[@id='spinner-container']")) {
			(new WebDriverWait(driver, 45, 200))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner-container")));
		}
	}

	public void pageLoadTime60Seconds(WebDriver driver) throws Exception {
		if (!checkIfcssSelectorExists(driver, "backdrop")) {
			(new WebDriverWait(driver, 60, 200))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("backdrop")));
		} else if (!checkIfXpathExists(driver, ".//*[@id='spinner-container']")) {
			(new WebDriverWait(driver, 60, 200))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner-container")));
		}
	}

	public boolean checkIfcssSelectorExists(WebDriver driver, String cssSelectorName) {
		boolean foundCssSelector = driver.findElements(By.className(cssSelectorName)).isEmpty();
		return foundCssSelector;
	}

	public boolean checkIfXpathExists(WebDriver driver, String xPath) {
		boolean foundXpath = true;
		for (int i = 0; i < 5; i++) {
			foundXpath = driver.findElements(By.xpath(xPath)).isEmpty();
			if (!foundXpath)
				break;
		}
		return foundXpath;
	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			WebDriverWait alertWait = new WebDriverWait(driver, 2);
			alertWait.until(ExpectedConditions.alertIsPresent());
			return true;
		} // try
		catch (Exception e) {
			return false;
		} // catch
	}

	public void clickOnWebElementUsingJavaScriptExecutor(WebDriver driver, WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element " + element.getText() + "+with using java script click");
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM " + e.getStackTrace());
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (Exception e) {
			System.out.println("Unable to click on element " + e.getStackTrace());
			throw new Exception("Unable to click on element and reason is " + e.getMessage());
		}
	}

	public void explicitWaitVisibility(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 80);
		if (element != null) {
			wait.until(ExpectedConditions.visibilityOf(element));
		} else {
			throw new WebDriverException("Element " + element + " is null");
		}
	}

	public void pageLoadTime30Seconds(WebDriver driver) throws Exception {
		if (!checkIfcssSelectorExists(driver, "backdrop")) {
			// System.out.println("Found the backdrop");
			// System.out.println("Waiting to invisibilityOfElementLocated by the
			// backdrop");
			(new WebDriverWait(driver, 30, 200))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("backdrop")));
		} else if (!checkIfXpathExists(driver, ".//*[@id='spinner-container']")) {
			// System.out.println("Found the spinner-container");
			// System.out.println("Waiting to invisibilityOfElementLocated by the
			// spinner-container");
			(new WebDriverWait(driver, 30, 200))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner-container")));
		}
	}

}
