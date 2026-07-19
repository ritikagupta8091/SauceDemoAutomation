package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(id = "user-name")
	WebElement usernameTxt;

	@FindBy(id = "password")
	WebElement passwordTxt;

	@FindBy(id = "login-button")
	WebElement loginBtn;

	public LoginPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	public void login(String username, String password) {

		wait.until(ExpectedConditions.visibilityOf(usernameTxt));

		usernameTxt.sendKeys(username);

		passwordTxt.sendKeys(password);

		wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

	}

}