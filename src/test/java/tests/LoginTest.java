package tests;

import com.itextpdf.text.DocumentException;
import org.testng.annotations.Test;
import tracks.utils.Config;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void verifyThatLoginPageContainsAllElements(){
        loginPage
                .assertThatLoginPageContainsAllElements(true);
    }

    @Test
    public void verifyThatUserCanLogIn() throws IOException, DocumentException {
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

}
