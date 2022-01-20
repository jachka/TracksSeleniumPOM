package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import tracks.pages.LoginPage;
import tracks.utils.Config;

public class BaseTest {
    protected ChromeDriver driver;
    protected LoginPage loginPage;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
    }
}


