package tracks.pages;

import com.itextpdf.text.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tracks.utils.Config;
import tracks.utils.Evidence;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

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

    @FindBy(xpath = "//a[contains(text(),'Projects')]")
    WebElement goToProjects;

/// VIEW PROJECT DETAILS PAGE ///
    @FindBy(xpath = "//span[@id='project_name']")
    WebElement projectNameView;

    @FindBy(xpath = "//div[@class='project_description']")
    WebElement projectDescriptionView;

    @FindBy(xpath = "//*[@class='project_settings']")
    WebElement projectSettingsView;

/// SORT ACTIVE PROJECTS ALPHABETICALLY ///
    @FindBy (xpath = "//a[@id='active_alphabetize_link']")
    WebElement sortActiveProjectsAlphabeticallyButton;


    public Projects addProject (String projectName, String projectDescription, String projectTag, Boolean evidence) throws IOException, DocumentException {

        //POPULATE 'ADD PRODUCT' FORM WITH VALID VALUES
        projectNameInput.sendKeys(projectName);
        projectDescriptionInput.sendKeys(projectDescription);
        projectTagInput.sendKeys(projectTag);

        goToProjectCheckbox.click();

        if (evidence) {
            Evidence.takeEvidence(driver, "addProject - New project form with values");
        }

        addProjectButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(projectNameView, projectName));

        if (evidence) {
            Evidence.takeEvidence(driver, "addProject - Details of a newly added project");
        }

        // OPEN NEWLY ADDED PROJECT AND VERIFY ITS DETAILS AGAINST THE VALUES PROVIDED DURING RECORD CREATION
        Assert.assertEquals(projectNameView.getText(), projectName);
        Assert.assertEquals(projectDescriptionView.getText(), projectDescription);
        Assert.assertTrue(projectSettingsView.getText().contains(projectTag));

        goToProjects.click();

        return this;
    }

    public void sortProjectsAlphabetically (Boolean evidence) throws IOException, DocumentException {
        //USE SORT ALPHABETICALLY FUNCTIONALITY ON ACTIVE PROJECTS
        sortActiveProjectsAlphabeticallyButton.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        //CAPTURE LIST AFTER SORTING
        if (evidence) {
            Evidence.takeEvidence(driver, "sortActiveProjectsAlphabetically - Projects list post sorting order");
        }

        //LOAD LISTS, SORT AND HANDLE COMPARISON
        WebElement listOfActiveProjects = driver.findElement(By.xpath("//div[@id='list-active-projects']"));
        List<WebElement> activeProjects = listOfActiveProjects.findElements(By.xpath("//div[@class='project_description']"));

        String[] projectListInitial = new String[activeProjects.size()];
        String[] projectListSorted = new String[activeProjects.size()];

        for(int i=0; i < activeProjects.size(); i++) {
            projectListInitial[i]=projectListSorted[i]=activeProjects.get(i).getText();
        }

        Arrays.sort(projectListSorted);

        for(int i=0; i < activeProjects.size(); i++) {
            Assert.assertEquals(projectListInitial[i], projectListSorted[i]);
        }

    }

    public Projects deleteProject (String projectName, Boolean evidence) throws IOException, DocumentException {

        if (evidence) {
            Evidence.takeEvidence(driver, "deleteProject - Project visible in the list");
        }

        //LOCALIZE PROPER PROJECT BY ITS NAME AND PRESS DELETE BUTTON, VERIFY ALERT TEXT
        WebElement deleteButton = driver.findElement(By.xpath("//a[contains(text(),'"+projectName+"')]/../../../a[@class='delete_project_button icon']"));
        deleteButton.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(driver.switchTo().alert().getText(), "Are you sure that you want to delete the project '"+projectName+"'?");

        //SELECT ACCEPT ON THE ALERT ON VERIFY IF PROJECT IS NO LONGER DISPLAYED
        driver.switchTo().alert().accept();

        //WebElement loader = driver.findElement(By.xpath("//img[@src='/assets/waiting-82b6c6429c34d99bf12aed096572cc22.gif']"));
        //WebDriverWait wait2 = new WebDriverWait(driver,5);
        //wait2.until(ExpectedConditions.invisibilityOf(loader));

        WebElement project = driver.findElement((By.xpath("//a[contains(text(),'"+projectName+"')]")));
        Assert.assertTrue(project.isDisplayed());

        if (evidence) {
            Evidence.takeEvidence(driver, "deleteProject - Project successfully removed from the list");
        }

        return this;
    }

}





