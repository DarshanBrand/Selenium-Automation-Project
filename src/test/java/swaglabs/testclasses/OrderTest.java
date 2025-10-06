package swaglabs.testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import swaglabs.POM.Order;
import swaglabs.baseclasses.Baseclass;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderTest extends Baseclass {

    @Test
    public void place_Order() throws InterruptedException
    {
        Order order = new Order(driver);

        String ProductName="Sauce Labs Onesie";

        order.clickOnProduct(ProductName);

        order.clickAddToCartButton();

        order.clickShoppingCartIcon();

       String Price=order.getPriceOfTheProductOnCartPage();

       System.out.println("The Price of the Product is : "+Price);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(Price,"$7.99");

        softAssert.assertAll();

        order.clickCheckoutButton();



        Thread.sleep(5000);


    }
}
