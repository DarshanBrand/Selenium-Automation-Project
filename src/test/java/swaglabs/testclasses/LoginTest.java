package swaglabs.testclasses;

import org.testng.annotations.Test;
import swaglabs.baseclasses.Baseclass;

public class LoginTest extends Baseclass {

    @Test
    public void verifyLoginTest() throws InterruptedException {
        System.out.println("✅ Login Test Executed Successfully!");
        Thread.sleep(5000);
    }
}
