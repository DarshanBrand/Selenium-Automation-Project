package swaglabs.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // 📌 Add timestamp to report name
            String timestamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss_").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/reports/extent/ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            reporter.config().setReportName("SwagLabs Automation Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Project", "SwagLabs");
            extent.setSystemInfo("Tester", "Darshan");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
