package tracks.pages;

import com.itextpdf.text.DocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tracks.utils.Evidence;
import java.lang.reflect.Method;

import java.io.IOException;


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

    @FindBy(xpath = "//div[@id=\"minilinks\"]/a")
    WebElement loggedInTxt;

    @FindBy(xpath = "//h4[@class=\"alert warning\"]")
    WebElement warningTxt;

    //Method to handle logging in
    public LoginPage logIntoTheApp(String username, String password, Boolean evidence) throws IOException, DocumentException {

        //Wait for elements to appear
        WebDriverWait wait = new WebDriverWait(driver, 10);
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

        return this;
    }

    //Method to assert if successfully logged in
    public void assertThatUserIsLoggedIn(String username, Boolean evidence) throws DocumentException, IOException {

        //Get method name that we will use to name a screenshot
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();

        //Wait for element to appear
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(loggedInTxt));

        //Check if user logged in (expected text must be visible)
        Assert.assertEquals(loggedInTxt.getText(), "Logout ("+username+") »");

        //Capture evidence
        if (evidence) {
            Evidence.takeEvidence(driver, methodName);

        }

    }

    //Method to assert if logging in failed
    public void assertThatUserIsNotLoggedIn(Boolean evidence){

        Assert.assertEquals(warningTxt.getText(), "Login unsuccessful.");

    }

    //Method to assert if all expected elements are visible
    public void assertThatLoginPageContainsAllElements(Boolean evidence){

    }

}
