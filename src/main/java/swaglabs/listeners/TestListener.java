package swaglabs.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import swaglabs.ExtentReports.ExtentManager;
import swaglabs.utils.ScreenshotUtils;
import swaglabs.baseclasses.Baseclass;

public class TestListener extends Baseclass implements ITestListener {

    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testReport.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testReport.get().log(Status.PASS, "✅ Test Passed: " + result.getMethod().getMethodName());

        try {
            // ✅ Capture Base64 screenshot for embedding
            String base64Screenshot = ScreenshotUtils.captureScreenshotBase64(driver);
            testReport.get().addScreenCaptureFromBase64String(base64Screenshot, "Success Screenshot");

            // ✅ Save PNG screenshot locally
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName() + "_PASSED");
            testReport.get().log(Status.INFO, "Screenshot saved at: " + screenshotPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testReport.get().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());

        try {
            String base64Screenshot = ScreenshotUtils.captureScreenshotBase64(driver);
            testReport.get().addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");

            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName());
            testReport.get().log(Status.INFO, "Screenshot saved at: " + screenshotPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testReport.get().log(Status.SKIP, "⚠️ Test Skipped: " + result.getMethod().getMethodName());

        try {
            String base64Screenshot = ScreenshotUtils.captureScreenshotBase64(driver);
            testReport.get().addScreenCaptureFromBase64String(base64Screenshot, "Skipped Screenshot");

            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getMethod().getMethodName() + "_SKIPPED");
            testReport.get().log(Status.INFO, "Screenshot saved at: " + screenshotPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // ✅ Generate Extent Report after all tests
    }
}
