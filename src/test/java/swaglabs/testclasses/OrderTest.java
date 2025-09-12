package swaglabs.testclasses;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import swaglabs.POM.Order;
import swaglabs.baseclasses.Baseclass;

import java.util.List;

public class OrderTest extends Baseclass {

    @Test
    public void place_Order() throws InterruptedException
    {
        Order order = new Order(driver);

        String productName="Sauce Labs Bolt T-Shirt";
        // Get all product names

        System.out.println("The Page title is >> "+ driver.getTitle());
        order.clickOnProduct(productName);
        Thread.sleep(5000);

    }
}
