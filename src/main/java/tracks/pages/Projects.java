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

public class Projects extends BasePage {
    public Projects(ChromeDriver driver) {
        super(driver);
    }

    /// ADD NEW PROJECT FORM ///
    @FindBy(id = "project_name")
    WebElement projectNameInput;

    @FindBy(id = "project_description")
    WebElement projectDescriptionInput;

    @FindBy(id = "project_default_tags")
    WebElement projectTagInput;

    @FindBy(id = "go_to_project")
    WebElement goToProjectCheckbox;

    @FindBy(id = "project_new_project_submit")
    WebElement addProjectButton;

    /// VIEW PROJECT DETAILS PAGE ///
    @FindBy(xpath = "//span[@id='project_name']")
    WebElement projectNameView;

    @FindBy(xpath = "//div[@class='project_description']")
    WebElement projectDescriptionView;

    @FindBy(xpath = "//*[@class='project_settings']")
    WebElement projectSettingsView;


    public Projects addProject (String projectName, String projectDescription, String projectTag, Boolean evidence) throws IOException, DocumentException {

        projectNameInput.sendKeys(projectName);
        projectDescriptionInput.sendKeys(projectDescription);
        projectTagInput.sendKeys(projectTag);

        goToProjectCheckbox.click();

        if (evidence) {
            Evidence.takeEvidence(driver, "addProject - New project form with values");
        }

        addProjectButton.click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.textToBePresentInElement(projectNameView, projectName));

        if (evidence) {
            Evidence.takeEvidence(driver, "addProject - Details of a newly added project");

        }
        Assert.assertEquals(projectNameView.getText(), projectName);
        Assert.assertEquals(projectDescriptionView.getText(), projectDescription);
        Assert.assertTrue(projectSettingsView.getText().contains(projectTag));

        return this;
        }

    }



