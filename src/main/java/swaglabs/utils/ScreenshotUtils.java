package swaglabs.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    /**
     * Capture screenshot and save as file.
     * Returns relative path so it can be used in Extent Reports.
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss_").format(new Date());

        // Save screenshot inside reports/screenshots folder
        String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
        String fileName = screenshotName + "_" + timestamp + ".png";
        String destPath = screenshotDir + fileName;

        try {
            // Ensure screenshots folder exists
            File dir = new File(screenshotDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Capture screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(destPath);
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return relative path (important for ExtentReports)
        return "screenshots/" + fileName;
    }

    /**
     * Capture screenshot as Base64 string.
     * Useful for embedding directly into Extent Reports.
     */
    public static String captureScreenshotBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
