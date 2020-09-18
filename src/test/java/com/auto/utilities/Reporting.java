package com.auto.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.auto.testCases.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting implements ITestListener {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	String workSpace = null;
	String jobName = System.getenv("JOB_NAME");
	public static Logger log = Logger.getLogger(BaseClass.class.getName());

	@Override
	public void onTestStart(ITestResult tr) {
		log.info("onTestStart is Started");
		logger = extent.createTest(tr.getName());
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log.info("onTestSkipped is Started");

		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		log.info("onTestSkipped is Ended");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
		logger = extent.createTest(tr.getName());

	}

	@Override
	public void onStart(ITestContext context) {
		log.info("onStart is Started");

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "test-report" + timeStamp + ".html";

		workSpace = System.getenv("WORKSPACE");
		if (workSpace != null) {
			htmlReporter = new ExtentHtmlReporter(workSpace+"/Reports/" + reportName);
			htmlReporter.loadConfig(System.getProperty("user.dir") + "/extent-config.xml");
			htmlReporter = new ExtentHtmlReporter(workSpace+"/Reports/" + reportName);

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			logger = extent.createTest("Test Report");

			extent.setSystemInfo("JOB_NAME", "jobName");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("user", "Anji");

			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle("Automation Report");
			htmlReporter.config().setReportName("Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.DARK);

		} else {
			htmlReporter = new ExtentHtmlReporter("./Reports/" + reportName);
			htmlReporter.loadConfig(System.getProperty("user.dir") + "/extent-config.xml");

			htmlReporter = new ExtentHtmlReporter("./Reports/" + reportName);
			htmlReporter.loadConfig(System.getProperty("user.dir") + "/extent-config.xml");

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			logger = extent.createTest("Test Report");

			extent.setSystemInfo("Host Name", "localhost");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("user", "Anji");

			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle("Automation Report");
			htmlReporter.config().setReportName("Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.DARK);
		}
		log.info("onStart is Ended");

	}

	@Override
	public void onFinish(ITestContext context) {

		context.getCurrentXmlTest();
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		log.info("onTestSuccess is Started");

		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

		try {
			String screenShot = BaseClass.captureScreen();
			logger.pass(tr.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenShot).build());
		} catch (IOException e) {
			e.printStackTrace();

		}
		log.info("onTestSuccess is Ended");

	}

	@Override
	public void onTestFailure(ITestResult tr) {
		log.info("onTestFailure is Started");

		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		try {
			String screenShot = BaseClass.captureScreen();
			logger.fail(tr.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenShot).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("onTestFailure is Ended");

	}

}
