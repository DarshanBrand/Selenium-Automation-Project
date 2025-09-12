package swaglabs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class Order {

    // Constructor
    public Order(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriver driver;

    // PageFactory Locators
    @FindBy (xpath = "//div[@class='inventory_item_label']//a")
    private List<WebElement>productList;



    public void clickOnProduct(String productName) {
        for (WebElement product : productList) {
            String name = product.getText().trim();
            System.out.println(" Checking product: " + name);

            if (name.equalsIgnoreCase(productName)) {
                System.out.println(" Clicking on product: " + name);
                product.click();
                return; // stop once found and clicked
            }
        }
        throw new RuntimeException(" Product not found: " + productName);
    }



}
