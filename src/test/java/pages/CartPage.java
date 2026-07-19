package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(className = "cart_item")
    private java.util.List<WebElement> cartProducts;


    @FindBy(id = "checkout")
    private WebElement checkoutButton;


    public int getCartProductCount() {

        return cartProducts.size();

    }


    public void clickCheckout() {

        checkoutButton.click();

    }

}