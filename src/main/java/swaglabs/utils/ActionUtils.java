package swaglabs.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionUtils {


    public WebDriver driver;

    // Constructor
    public ActionUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollIntoView(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void click(WebElement element) {
        scrollIntoView(element);
        element.click();
    }

    public void sendKeysElement(WebElement element, String text) {
        scrollIntoView(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getTextElement(WebElement element) {
        scrollIntoView(element);
        return element.getText().trim();
    }
}
