package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import tracks.pages.LoginPage;
import tracks.pages.Projects;
import tracks.utils.Config;

import java.util.concurrent.TimeUnit;

import static org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration.seconds;

public class BaseTest {
    protected ChromeDriver driver;
    protected LoginPage loginPage;

    public static Logger logs = LogManager.getLogger();

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        if (driver != null) {
            logs.info("ChromeDriver properly initiated - test execution started.");
        } else {
            logs.info("Failed to initiate ChromeDriver  - Houston, we have a problem");
        }

    }

    @BeforeMethod()
    public void setUp() {
        driver.get(Config.getBaseUrl());
        loginPage = new LoginPage(driver);
    }

    @AfterMethod()
    public void afterMethod() {
        driver.manage().deleteAllCookies();
        driver.getSessionStorage().clear();
        driver.getLocalStorage().clear();
    }

    @AfterClass()
    public void tearDown(){
        driver.quit();
        logs.info("ChromeDriver terminated - test closed.");

    }
}


