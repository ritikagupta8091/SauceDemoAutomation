package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver webDriver;

    @BeforeMethod
    @Parameters("browser")
    public void launchBrowser(String browser) {

        System.out.println("Launching Browser : " + browser);

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");

            webDriver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            webDriver = new FirefoxDriver();

        } else {

            throw new RuntimeException("Browser not supported : " + browser);
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        webDriver.get("https://www.saucedemo.com/");
    }

    // This method is required by FinalTest and ExtentReportListener
    public WebDriver getDriver() {
        return webDriver;
    }

    @AfterMethod
    public void closeBrowser() {

        if (webDriver != null) {
            System.out.println("Closing Browser");
            webDriver.quit();
        }
    }
}