package tests;

import com.itextpdf.text.DocumentException;
import org.testng.annotations.Test;
import tracks.utils.Config;

import java.io.IOException;

public class ProjectTest extends BaseTest {


    @Test
    public void verifyThatUserCanAddNewProject() throws IOException, DocumentException {
        loginPage
                .logIntoTheApp(Config.user, Config.password, false)
                .goToProjects()
                .addProject("This is project Name 05","This is description of test 05","tag5", true );
    }
}