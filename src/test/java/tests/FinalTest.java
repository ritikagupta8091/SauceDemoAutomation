package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import listener.ExtentReportListener;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import testdata.LoginData;

public class FinalTest extends BaseTest {

	@Test(dataProvider = "orderData", dataProviderClass = LoginData.class)
	public void completeOrderTest(String username, String password) {

		LoginPage login = new LoginPage(getDriver());

		login.login(username, password);
		Reporter.log("Login Successful for : " + username, true);

		ExtentReportListener.test.log(Status.INFO, "Login Successful : " + username);

		InventoryPage inventory = new InventoryPage(getDriver());

		Assert.assertEquals(inventory.getProductCount(), 6);

		inventory.addProductToCart("Sauce Labs Backpack");

		Assert.assertEquals(inventory.getButtonText("Sauce Labs Backpack"), "Remove");

		Reporter.log("Product Added Successfully", true);

		ExtentReportListener.test.log(Status.INFO, "Product Added Successfully");

		inventory.clickCart();

		CartPage cart = new CartPage(getDriver());

		Assert.assertEquals(cart.getCartProductCount(), 1);

		cart.clickCheckout();

		CheckoutPage checkout = new CheckoutPage(getDriver());

		checkout.enterCheckoutDetails("Ritika", "Gupta", "110052");

		Reporter.log("Checkout Details Entered", true);

		checkout.clickContinue();

		checkout.finishOrder();

		Assert.assertEquals(checkout.getSuccessMessage(), "Thank you for your order!");
		Reporter.log("Order Completed Successfully", true);

		ExtentReportListener.test.log(Status.PASS, "Order Completed Successfully");

	}

}