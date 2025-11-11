package swaglabs.testclasses;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import swaglabs.POM.Order;
import swaglabs.baseclasses.Baseclass;
import swaglabs.utils.JsonUtils;

public class OrderTest extends Baseclass {

    @Test
    public void place_Order() throws InterruptedException {
        // Create an instance of the Order Page using the WebDriver instance
        Order order = new Order(driver);

       // Define the product name to be selected
        String ProductName = "Sauce Labs Onesie";

       // Click on the specified product to open its details page
        order.clickOnProduct(ProductName);

       // Click the 'Add to Cart' button on the product details page
        order.clickAddToCartButton();

       // Click on the shopping cart icon to navigate to the cart page
        order.clickShoppingCartIcon();

       // Get the price of the selected product from the cart page
        String Price = order.getPriceOfTheProductOnCartPage();

       // Print the product price in the console for reference
        System.out.println("The Price of the Product is : " + Price);

       // Create a SoftAssert instance for validation without immediate test termination
        SoftAssert softAssert = new SoftAssert();

       // Verify that the product price in the cart matches the expected value
        softAssert.assertEquals(Price, "$7.99", "Product price mismatch!");

       // Collate all soft assertions (marks test failed if any assertion fails)
        softAssert.assertAll();

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



        Thread.sleep(10000);


    }
}
