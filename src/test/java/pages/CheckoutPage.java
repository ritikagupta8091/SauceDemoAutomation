package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(id = "first-name")
	WebElement firstName;

	@FindBy(id = "last-name")
	WebElement lastName;

	@FindBy(id = "postal-code")
	WebElement postalCode;

	@FindBy(id = "continue")
	WebElement continueButton;

	@FindBy(id = "finish")
	WebElement finishButton;

	@FindBy(className = "complete-header")
	WebElement successMessage;

	public CheckoutPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void enterCheckoutDetails(String fname, String lname, String zip) {

		wait.until(ExpectedConditions.elementToBeClickable(firstName));

		firstName.sendKeys(fname);

		wait.until(ExpectedConditions.elementToBeClickable(lastName));

		lastName.sendKeys(lname);

		wait.until(ExpectedConditions.elementToBeClickable(postalCode));

		postalCode.sendKeys(zip);

	}

	public void clickContinue() {

		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();

		wait.until(ExpectedConditions.urlContains("checkout-step-two"));

	}

	public void finishOrder() {

		wait.until(ExpectedConditions.visibilityOf(finishButton));

		wait.until(ExpectedConditions.elementToBeClickable(finishButton));

		finishButton.click();

	}

	public String getSuccessMessage() {

		wait.until(ExpectedConditions.visibilityOf(successMessage));

		return successMessage.getText();

	}

}