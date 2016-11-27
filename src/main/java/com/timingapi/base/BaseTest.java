package com.timingapi.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by artyom
 */
public class BaseTest {
    private final String URL = "http://192.168.99.100:4444//wd/hub";

    public WebDriver driver;
    DesiredCapabilities capabilities;

    @DataProvider(name = "test1", parallel = true)
    public static Object[][] primeNumbers() {
        return new Object[][]{{1}, {2}};
    }

    public WebDriver setUp(Browsers browser) {
        System.setProperty("selenide.reports", "target/selenide/tests");

        if (browser == Browsers.Chrome) {
            capabilities = DesiredCapabilities.chrome();
        }
        if (browser == Browsers.Firefox) {
            capabilities = DesiredCapabilities.firefox();
        }
        if (browser == Browsers.IE) {
            capabilities = DesiredCapabilities.internetExplorer();
        }
        if (browser == Browsers.PhantomJS) {
            capabilities = DesiredCapabilities.phantomjs();
            capabilities.setBrowserName("phantomjs");
        }
        try {
            return driver = new RemoteWebDriver(new URL(URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @AfterTest
    public void close() {
        driver.quit();
    }

    public static Map<String, Long> stepsTiming = new HashMap<>();

    public static void collect(String step, Long time) {
        stepsTiming.put(step, time);
    }

}
