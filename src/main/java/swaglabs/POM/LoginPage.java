package swaglabs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import swaglabs.utils.ActionUtils;

public class LoginPage {


    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriver driver;
    ActionUtils actionUtils;

    // PageFactory Locators
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginBtn;


    // Login Action
    public void login(String username, String password) {

        actionUtils = new ActionUtils(driver);
        actionUtils.sendKeysElement(usernameField,username);
        actionUtils.sendKeysElement(passwordField,password);
        actionUtils.clickElement(loginBtn);
    }
}
