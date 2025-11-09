package swaglabs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import swaglabs.utils.ActionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    public WebDriver driver;
    public ActionUtils actionUtils;

    // Constructor
    public Order(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actionUtils = new ActionUtils(driver);
    }

    // PageFactory Locators
    @FindBy (xpath = "//div[@class='inventory_item_label']//a")
    private List<WebElement>productList;

    @FindBy(css = ".inventory_details_price")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@data-test='inventory-item-price']/following-sibling::button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='shopping_cart_container']//a")
    private WebElement shoppingCartIcon;

    @FindBy(xpath = "//div[@class='item_pricebar']//div")
    private WebElement cartItemPrice;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    //=======================================================================================================

    public void clickOnProduct(String productName) {
        for (WebElement product : productList) {
            String name = product.getText().trim();
            System.out.println(" Checking product: " + name);

            if (name.equalsIgnoreCase(productName)) {
                System.out.println(" Clicking on product: " + name);
                actionUtils.clickElement(product);
                return; // stop once found and clicked
            }
        }
        throw new RuntimeException(" Product not found: " + productName);
    }

    public String getPriceOfTheProductOnProductsPage() {
        return productPrice.getText().trim();   // return value to test class
    }

    public void clickAddToCartButton() {

        actionUtils.clickElement(addToCartButton);
    }

    public void clickShoppingCartIcon() {

        actionUtils.clickElement(shoppingCartIcon);
    }

    public String getPriceOfTheProductOnCartPage() {
        return cartItemPrice.getText().trim();   // return value to test class
    }


    public void clickCheckoutButtonSignup() {
        checkoutButton.click();
    }







    }




