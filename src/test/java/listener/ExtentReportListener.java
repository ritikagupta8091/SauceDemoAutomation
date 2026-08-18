package listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import tests.BaseTest;

public class ExtentReportListener implements ITestListener {

    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        ExtentSparkReporter spark =
                new ExtentSparkReporter("test-output/ExtentReport.html");

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("SauceDemo Automation Report");
        spark.config().setReportName("Selenium TestNG Automation Execution");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project", "SauceDemo Automation");
        extent.setSystemInfo("Tester", "Ritika Gupta");
        extent.setSystemInfo("Framework", "Selenium + TestNG + Maven");
        extent.setSystemInfo("Browser", "Chrome / Firefox");
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().log(Status.PASS, "Test Passed Successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().log(Status.FAIL, result.getThrowable());

        try {

            BaseTest base = (BaseTest) result.getInstance();

            String screenshotPath =
                    ((TakesScreenshot) base.getDriver())
                    .getScreenshotAs(OutputType.FILE)
                    .getAbsolutePath();

            test.get().fail(
                    "Screenshot on Failure",
                    MediaEntityBuilder
                            .createScreenCaptureFromPath(screenshotPath)
                            .build()
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}