package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // Product List
    @FindBy(className = "inventory_item")
    List<WebElement> products;

    // Cart Icon
    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    // Add Product
    public void addProductToCart(String productName) {
      
        driver.findElement(By.xpath("//div[text()='" + productName
                + "']/ancestor::div[@class='inventory_item']//button")).click();

    }

    // Verify Button Text
    public String getButtonText(String productName) {

        return driver.findElement(By.xpath("//div[text()='" + productName
                + "']/ancestor::div[@class='inventory_item']//button")).getText();

    }

    // Product Count
    public int getProductCount() {

        return products.size();

    }

    // Open Cart
    public void clickCart() {

        cartIcon.click();

    }


}