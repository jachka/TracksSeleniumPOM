package tracks.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class BasePage {

    @FindBy(xpath = "//*[@title='Projects']")
    WebElement projects;

    public static String HOME_PAGE_TITLE = "TRACKS::List tasks";
    public static String STARRED_PAGE_TITLE = "TRACKS::Tagged with 'starred'";
    public static String PROJECTS_PAGE_TITLE = "TRACKS::List Projects";
    public static String TICKLER_PAGE_TITLE = "TRACKS::Tickler";
    public static String CONTEXTS_PAGE_TITLE = "TRACKS::List Contexts";
    public static String NOTES_PAGE_TITLE = "TRACKS::All Notes";
    public static String REVIEW_PAGE_TITLE = "TRACKS::Review";
    public static String TODOS_PAGE_TITLE = "TRACKS::Recurring Actions";
    public static String CALENDAR_PAGE_TITLE = "TRACKS::Calendar";
    public static String DONE_PAGE_TITLE = "TRACKS::Done";
    public static String FEEDS_PAGE_TITLE = "TRACKS::Feeds";
    public static String STATISTICS_PAGE_TITLE = "TRACKS::Statistics";
    public static String PREFERENCES_PAGE_TITLE = "TRACKS::Preferences";
    public static String EXPORT_PAGE_TITLE = "TRACKS::Export";
    public static String IMPORT_PAGE_TITLE = "TRACKS::Import";
    public static String MANAGE_USERS_PAGE_TITLE = "TRACKS::Manage Users";
    public static String INTEGRATE_PAGE_TITLE = "TRACKS::Integrations";
    public static String REST_PAGE_TITLE = "TRACKS::REST API Documentation";

    public static Logger logs = LogManager.getLogger();

    protected ChromeDriver driver;

    public BasePage (ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


}
