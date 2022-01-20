package tracks.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.itextpdf.text.DocumentException;
import org.openqa.selenium.chrome.ChromeDriver;
import tracks.pages.BasePage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evidence extends BasePage {

    public Evidence (ChromeDriver driver) {
        super(driver);
    }
    public static void takeEvidence (WebDriver driver, String name) throws DocumentException, IOException {

            String path = Config.path;

        //Prepare timestamp
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH.mm.ss");
        LocalDateTime now = LocalDateTime.now();
        String timeStamp = dtf.format(now);

        //Handle screenshot
        TakesScreenshot evidence = ((TakesScreenshot)driver);
        File EvidenceFile = evidence.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(EvidenceFile, new File(path+name+"_"+timeStamp+".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}