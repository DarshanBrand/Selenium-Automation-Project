package swaglabs.testclasses;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import swaglabs.POM.Checkout;
import swaglabs.POM.Order;
import swaglabs.baseclasses.Baseclass;
import swaglabs.utils.JsonUtils;

public class OrderTest extends Baseclass {

    @Test
    public void place_Order() throws InterruptedException {

        // Create an instance of the Order Page using the WebDriver instance
        Order order = new Order(driver);
        Checkout checkout =new Checkout(driver);

       // Define the product name to be selected
        String Cart_ProductName = "Sauce Labs Onesie";

       // Click on the specified product to open its details page
        order.clickOnProduct(Cart_ProductName);

       // Click the 'Add to Cart' button on the product details page
        order.clickAddToCartButton();

       // Click on the shopping cart icon to navigate to the cart page
        order.clickShoppingCartIcon();

       // Get the price of the selected product from the cart page
        String Cart_Price = order.getPriceOfTheProductOnCartPage();

       // Print the product price in the console for reference
        System.out.println("The Price of the Product is : " + Cart_Price);

       // Create a SoftAssert instance for validation without immediate test termination
        SoftAssert softAssert = new SoftAssert();

       // Verify that the product price in the cart matches the expected value
        softAssert.assertEquals(Cart_Price, "$7.99", "Product price mismatch!");

       // Click on the 'Checkout' button to proceed to the checkout page
        order.clickCheckoutButton();

        JsonObject data = JsonUtils.getJsonData("orderdata.json");

        // Step 2: Access the "customers" array
        JsonArray customers = data.getAsJsonArray("customers");

        // Step 3: Access the first object (index 0)
        JsonObject firstCustomer = customers.get(0).getAsJsonObject();

        // Step 4: Extract customer details from JSON and enter them into the form

        // Extract and enter the first name from JSON data
        String firstName = firstCustomer.get("firstName").getAsString();
        order.enterFirstName(firstName);

        // Extract and enter the last name from JSON data
        String lastName = firstCustomer.get("lastName").getAsString();
        order.enterLastName(lastName);

       // Extract and enter the postal/ZIP code from JSON data
        String zipCode = firstCustomer.get("zipCode").getAsString();
        order.enterPostalCode(zipCode);

        // Click on the Continue button to proceed with the next step
        order.clickContinueButton();

        // Retrieve the product name displayed on the checkout page
        String checkout_ProductName = checkout.getNameOfTheProductOnCheckoutPage();

        // Print the product name for verification
        System.out.println("The product name displayed on the checkout page is: " + checkout_ProductName);

        // Validate that the product name displayed on the checkout page matches the product name from the cart
        Assert.assertEquals(checkout_ProductName, Cart_ProductName);

       // Retrieve the product price displayed on the checkout page
        String checkout_ProductPrice = checkout.getPriceOfTheProductOnCheckoutPage();

        // Print the product price for verification
        System.out.println("The product price displayed on the checkout page is: " + checkout_ProductPrice);

        // Validate that the product price displayed on the checkout page matches the product price from the cart
        Assert.assertEquals(checkout_ProductPrice, Cart_Price);

        // Click on the Finish button to complete the checkout process
        checkout.clickOnFinishButton();

        // Retrieve the success header message displayed after successful checkout
        String SuccessMessage = checkout.getSuccessHeaderMessage();

       // Print the success message for verification
        System.out.println("Success Message is: " + SuccessMessage);

       // Validate that the success message matches the expected confirmation text
        Assert.assertEquals(SuccessMessage, "Thank you for your order!");

        // Collate all soft assertions (marks test failed if any assertion fails)
        softAssert.assertAll();


        Thread.sleep(10000);


    }
}
