import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        ChromeDriver driver = new ChromeDriver(options);

        driver.get("https://av.brest.by/info.html#phone");

        String aPath = "//a[@href='/docum/zakon_avto.docx']";
        WebElement doc = driver.findElement(By.xpath(aPath));

        doc.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.close();


        File folder = new File(System.getProperty("user.dir"));

        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File f = null;

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                if (fileName.matches("zakon_avto.docx")) {
                    f = new File(fileName);
                    found = true;
                }
            }
        }
        if (f == null) {
            System.out.println("File not found");
            f.deleteOnExit();
            return;
        }
        boolean isRunFound = TestDoc.findTextInDoc(f, "ЗАКОН РЕСПУБЛИКИ БЕЛАРУСЬ");
        if (isRunFound) {
            System.out.println("File is correct");
        } else {
            System.out.println("File isn't correct");
        }

        f.deleteOnExit();
    }
}

