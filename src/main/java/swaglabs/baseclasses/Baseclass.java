package swaglabs.baseclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import swaglabs.POM.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Baseclass {

        public static WebDriver driver;
        public static Properties config;
        public static String environment;
        public static String browser;

        @BeforeTest
        public void setUp() throws IOException {
            loadConfig();
            initializeBrowser();
            loginToApplication();
        }

        // Load config.properties
        public void loadConfig() throws IOException {
            config = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties"); // ✅ correct path
            config.load(fis);

            environment = config.getProperty("environment").toLowerCase();
            browser = config.getProperty("browser").toLowerCase();
        }

        // Launch browser based on config
        public void initializeBrowser() {
            if (browser.equals("firefox")) {
                 driver = new FirefoxDriver();

            } else if (browser.equals("edge")) {
                driver = new EdgeDriver();

            } else if (browser.equals("chrome")) {
                ChromeOptions options = new ChromeOptions();

                // ✅ Disable Chrome password manager & leak detection popup
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);        // disable credentials service
                prefs.put("profile.password_manager_enabled", false);  // disable password manager
                prefs.put("profile.password_manager_leak_detection", false); // disable leak detection

                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");

                driver = new ChromeDriver(options);

            } else {
                throw new RuntimeException("❌ Browser not supported: " + browser);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Load environment-specific URL
            String url = config.getProperty(environment + ".url");
            if (url == null) {
                throw new RuntimeException("❌ URL not defined for environment: " + environment);
            }
            driver.get(url);
        }

        // Perform login with environment-specific credentials
        public void loginToApplication() {
            LoginPage loginPage = new LoginPage(driver);

            String username = config.getProperty(environment + ".username");
            String password = config.getProperty(environment + ".password");

            if (username == null || password == null) {
                throw new RuntimeException("❌ Username/Password not defined for environment: " + environment);
            }

            loginPage.login(username, password);
        }

        @AfterTest
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
}
