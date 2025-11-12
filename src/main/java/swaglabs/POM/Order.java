package swaglabs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import swaglabs.utils.ActionUtils;

import java.util.List;

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

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueBtn;




    //=======================================================================================================

    public void clickOnProduct(String productName) {
        for (WebElement product : productList) {
            String name = product.getText().trim();
            System.out.println(" Checking product: " + name);

            if (name.equalsIgnoreCase(productName)) {
                System.out.println(" Clicking on product: " + name);
                actionUtils.click(product);
                return; // stop once found and clicked
            }
        }
        throw new RuntimeException(" Product not found: " + productName);
    }

    public String getPriceOfTheProductOnProductsPage() {
        return productPrice.getText().trim();   // return value to test class
    }

    public void clickAddToCartButton() {

        actionUtils.click(addToCartButton);
    }

    public void clickShoppingCartIcon() {

        actionUtils.click(shoppingCartIcon);
    }

    public String getPriceOfTheProductOnCartPage() {
        return cartItemPrice.getText().trim();   // return value to test class
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public void enterFirstName(String firstName) {
        this.firstName.clear();
        this.firstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        this.lastName.clear();
        this.lastName.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        this.postalCode.clear();
        this.postalCode.sendKeys(postalCode);
    }

    public void clickContinueButton() {
        actionUtils.scrollIntoView(continueBtn);
        continueBtn.click();
    }









}




