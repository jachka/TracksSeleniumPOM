package tests;

import com.itextpdf.text.DocumentException;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;
import tracks.utils.Config;

import java.io.IOException;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {


    @Test
    public void verifyThatUserCanLogInUsingValidCredentials() throws IOException, DocumentException {
        loginPage
                .logIntoTheApp(Config.user, Config.password,  false)
                .assertThatUserIsLoggedIn(Config.user, true);
    }

    @Test
    public void verifyThatUserCannotLoginWithInvalidCredentials() throws IOException, DocumentException {
        loginPage
                .logIntoTheApp("blabla", "bleble", true)
                .assertThatUserIsNotLoggedIn(true);
    }

    @Test
    public void verifyThatUserCannotLoginWhenCredentialsLeftEmpty() throws IOException, DocumentException {
        loginPage
                .logIntoTheApp("", "", true)
                .assertThatUserIsNotLoggedIn(true);
    }

    @Test
    public void verifyIfUserCanOpenProjects() throws IOException, DocumentException {
        loginPage
                .logIntoTheApp(Config.user,Config.password, true)
                .goToProjects();
    }

}
