package tracks.pages;

import com.itextpdf.text.DocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tracks.utils.Evidence;

import java.io.IOException;
import java.time.Duration;

public class MainPage extends BasePage{
    public MainPage(ChromeDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id=\"minilinks\"]/a")
    WebElement loggedInTxt;

    @FindBy(xpath = "//h4[@class=\"alert warning\"]")
    WebElement warningTxt;

    @FindBy(xpath = "//h2[contains(., 'Active projects')]")
    WebElement projectsHeader;

    //Method to assert if successfully logged in
    public void assertThatUserIsLoggedIn(String username, Boolean evidence) throws DocumentException, IOException {

        //Wait for element to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loggedInTxt));

        //Check if user logged in (expected text must be visible)
        Assert.assertEquals(loggedInTxt.getText(), "Logout ("+username+") »");


        //Capture evidence
        if (evidence) {
            Evidence.takeEvidence(driver, "assertThatUserIsLoggedIn");

        }

    }

    //Method to assert if logging in failed
    public void assertThatUserIsNotLoggedIn(Boolean evidence){

        Assert.assertEquals(warningTxt.getText(), "Login unsuccessful.");

    }

    public Projects goToProjects(){
        projects.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(projectsHeader));
        Assert.assertEquals(driver.getTitle(),PROJECTS_PAGE_TITLE);

        return new Projects(driver);
    }
}
