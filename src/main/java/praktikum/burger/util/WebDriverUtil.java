package praktikum.burger.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class WebDriverUtil {

    public WebDriver getWebDriver() {
        String browserName = getBrowserName();
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                ChromeOptions options = new ChromeOptions();
                return new ChromeDriver(options);
            default:
                throw new IllegalArgumentException("Invalid browser name specified in config.properties");
        }
    }

    public String getBrowserName() {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/java/praktikum/burger/util/config.properties");
            property.load(fis);

            return property.getProperty("browser");

        } catch (IOException e) {
            System.err.println("ERROR! No property file!");
            return null;
        }
    }

}

