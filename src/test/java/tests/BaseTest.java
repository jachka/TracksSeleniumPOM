package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import tracks.pages.LoginPage;
import tracks.pages.Projects;
import tracks.utils.Config;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected ChromeDriver driver;
    protected LoginPage loginPage;

    public static Logger logs = LogManager.getLogger();

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
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


