package com.timingapi.base;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by artyom
 */
public class BaseTest {
    private final String URL = "http://192.168.99.100:4444//wd/hub";

    WebDriver driver;
    DesiredCapabilities capabilities;
    @DataProvider(name = "test1", parallel = true)
    public static Object[][] primeNumbers() {
        return new Object[][] {{1}};
    }

    public WebDriver setUp(Browsers browser) {
        System.setProperty("selenide.reports", "target/selenide/tests");

        if (browser == Browsers.Chrome) {
            capabilities = DesiredCapabilities.chrome();
        }
        if (browser == Browsers.Firefox){
            capabilities = DesiredCapabilities.firefox();
        }
        if (browser == Browsers.IE){
            capabilities = DesiredCapabilities.internetExplorer();
        }
        if (browser == Browsers.PhantomJS){
            capabilities = DesiredCapabilities.phantomjs();
            capabilities.setBrowserName("phantomjs");
        }
        try {
            return driver = new RemoteWebDriver(new URL(URL),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @AfterTest
    public void stop() {
        driver.quit();

    }
}
