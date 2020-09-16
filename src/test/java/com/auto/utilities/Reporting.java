package com.auto.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	@BeforeClass
	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "test-report" + timeStamp + ".html";

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + reportName);
		htmlReporter.loadConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Anji");

		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@AfterTest
	public void onTestSuccess(ITestResult tr) {

		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

	}

	@AfterTest
	public void onTestFailure(ITestResult tr) {

		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		String screenShotPath = System.getProperty("user.dir") + "\\Screenshots" + tr.getName() + ".png";

		File file = new File(screenShotPath);

		if (file.exists()) {
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenShotPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@AfterTest
	public void onTestSkipeed(ITestResult tr) {

		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));

	}
	
	/*@AfterMethod
    public void getResult(ITestResult result){
    if(result.getStatus() == ITestResult.FAILURE){
    //logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
    //MarkupHelper is used to display the output in different colors
    logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
    logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
    }else if(result.getStatus() == ITestResult.SKIP){
    //logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
    logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
    }
    }
	*/
	@AfterClass	
	public void onFinish() {
		extent.flush();
	}

}
