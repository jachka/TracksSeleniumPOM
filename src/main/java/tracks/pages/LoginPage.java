package tracks.pages;

import com.itextpdf.text.DocumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tracks.utils.Evidence;
import java.lang.reflect.Method;

import java.io.IOException;
import java.time.Duration;


public class LoginPage extends BasePage {
    public LoginPage(ChromeDriver driver) {
        super(driver);
    }

    //Find elements on the Login page
    @FindBy(xpath = "//input[@id=\"user_login\"]")
    WebElement usernameTxtField;

    @FindBy(xpath = "//input[@id=\"user_password\"]")
    WebElement passwordTxtField;

    @FindBy(xpath = "//input[@id=\"user_noexpiry\"]")
    WebElement stayLoggedInCheckbox;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    WebElement signInButton;



    //Method to handle logging in
    public MainPage logIntoTheApp(String username, String password, Boolean evidence) throws IOException, DocumentException {


        //Wait for elements to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(usernameTxtField, passwordTxtField, stayLoggedInCheckbox, signInButton));

        //Provide credentials
        usernameTxtField.sendKeys(username);
        passwordTxtField.sendKeys(password);

        //Expected: checkbox is not selected
        if (stayLoggedInCheckbox.isSelected())
        {
            stayLoggedInCheckbox.click();
        }

            // All good, let's log in
            signInButton.click();

        return new MainPage(driver);
    }



}
