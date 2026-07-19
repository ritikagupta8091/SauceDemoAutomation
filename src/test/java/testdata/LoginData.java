package testdata;

import org.testng.annotations.DataProvider;

public class LoginData {

	@DataProvider(name = "orderData")
	public Object[][] loginData() {

		return new Object[][] {

				{ "standard_user", "secret_sauce" },

				{ "performance_glitch_user", "secret_sauce" }

		};

	}

}