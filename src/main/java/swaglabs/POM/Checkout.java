package swaglabs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import swaglabs.utils.ActionUtils;

public class Checkout {

    public WebDriver driver;
    public ActionUtils actionUtils;

    // Constructor
    public Checkout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actionUtils = new ActionUtils(driver);
    }

    @FindBy(css = ".inventory_item_name")
    private WebElement checkoutProductName;

    @FindBy(css = ".inventory_item_price")
    private WebElement checkoutProductPrice;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    @FindBy(css = "#checkout_complete_container .complete-header")
    private WebElement headerSuccessMessage;



    public String getNameOfTheProductOnCheckoutPage()
    {
       return checkoutProductName.getText().trim();
    }

    public String getPriceOfTheProductOnCheckoutPage()
    {
        return checkoutProductPrice.getText().trim();
    }

    public void clickOnFinishButton()
    {
        actionUtils.click(finishBtn);
    }

    public String getSuccessHeaderMessage()
    {
        return headerSuccessMessage.getText().trim();
    }

}
