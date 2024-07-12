package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory 
{
	private static WebDriver driver;
    private static Properties configProps;

    public static WebDriver getDriver() {
        if (driver == null) {
            configProps = new Properties();
            try {
            	String path =System.getProperty("user.dir")+"//src//test//resources//config.properties";
                FileInputStream fis = new FileInputStream(path);
                configProps.load(fis);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Unable to load config.properties");
            }

            String browser = configProps.getProperty("browser");

            if (browser.equalsIgnoreCase("chrome")) {
               WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
            	WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
