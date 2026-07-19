package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	public static void takeScreenshot(WebDriver driver, String testName) {

		try {

			TakesScreenshot ts = (TakesScreenshot) driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

			File destination = new File("/screenshot.png" + testName + "_" + time + ".png");

			FileUtils.copyFile(source, destination);

			System.out.println("Screenshot Saved : " + destination);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}