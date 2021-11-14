package io.ctdev.framework.driver;

import io.ctdev.framework.config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingleton {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {

            switch (TestConfig.cfg.browser()) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                }
                default: {
                    if (TestConfig.cfg.remote()) {
                        try {
                            System.out.println((TestConfig.cfg.remote()));
                            DesiredCapabilities capabilities = new DesiredCapabilities();
                            capabilities.setBrowserName("chrome");
                            capabilities.setVersion("91.0");
                            capabilities.setCapability("enableVNC", true);
                            capabilities.setCapability("enableVideo", false);
                            driver.set(new RemoteWebDriver(new URL(TestConfig.cfg.remoteUrl()), capabilities));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        WebDriverManager.chromedriver().setup();
                        driver.set(new ChromeDriver());
                    }
                }
            }
            driver.get().manage().window().maximize();
        }
        return driver.get();

    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}