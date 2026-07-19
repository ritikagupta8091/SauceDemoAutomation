package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	@BeforeMethod
	@Parameters("browser")
	public void launchBrowser(String browser) {

		Reporter.log("Launching Browser : " + browser, true);

		WebDriver webDriver;

		if (browser.equalsIgnoreCase("edge")) {

			webDriver = new EdgeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {

			FirefoxOptions options = new FirefoxOptions();

			options.addArguments("--width=1920");
			options.addArguments("--height=1080");

			webDriver = new FirefoxDriver(options);

		} else {

			throw new RuntimeException("Browser not supported : " + browser);

		}

		driver.set(webDriver);

		getDriver().manage().window().maximize();

		getDriver().get("https://www.saucedemo.com/");

	}

	public static WebDriver getDriver() {

		return driver.get();

	}

	@AfterMethod
	public void closeBrowser() {

		Reporter.log("Closing Browser", true);

		if (getDriver() != null) {

			getDriver().quit();

			driver.remove();

		}

	}

}