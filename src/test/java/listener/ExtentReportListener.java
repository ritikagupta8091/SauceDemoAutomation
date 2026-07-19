package listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import tests.BaseTest;

public class ExtentReportListener implements ITestListener {

	public static ExtentReports extent;
	public static ExtentTest test;

	public void onStart(ITestContext context) {

		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");

		extent = new ExtentReports();

		extent.attachReporter(spark);

		extent.setSystemInfo("Project", "QKart Automation");
		extent.setSystemInfo("Tester", "Ritika");
		extent.setSystemInfo("Browser", "Chrome/Firefox");

	}

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Test Passed");

	}

	public void onTestFailure(ITestResult result) {

		test.log(Status.FAIL, result.getThrowable());

		try {

			BaseTest base = (BaseTest) result.getInstance();

			String path = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.FILE).getAbsolutePath();

			test.addScreenCaptureFromPath(path);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void onFinish(ITestContext context) {

		extent.flush();

	}

}