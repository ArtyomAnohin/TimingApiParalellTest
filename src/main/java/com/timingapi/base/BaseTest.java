package com.timingapi.base;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static com.timingapi.base.TimingAPI.saveHtmlAttachAll;
import static com.timingapi.base.TimingAPI.saveHtmlAttachAllTable;

/**
 * Created by artyom
 */
public class BaseTest {
    private final String URL = "http://192.168.99.100:4444//wd/hub";
    public WebDriver driver;
    DesiredCapabilities capabilities;

    public static List<CollectStepsTime.Item> listUsersTiming = new ArrayList<>();

    @DataProvider(name = "test1", parallel = true)
    public static Object[][] primeNumbers() {
        return new Object[][]{{1, "vasia", "pupkin"}, {2, "admin", "123"}};
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

    public static Map<String, PageLoadTiming> stepsTiming = new LinkedHashMap<>();

    public static void collectPerUser(String step, PageLoadTiming time) {
        stepsTiming.put(camelCaseToNormal(step), time);
    }

    public void collectTimeEachUser(Integer inputNumber) {
        List<CollectStepsTime.StepTime> list = new ArrayList<>();
        stepsTiming.forEach((k, v) -> list.add(new CollectStepsTime.StepTime(k, v)));
        listUsersTiming.add(new CollectStepsTime.Item("User " + inputNumber, list));
    }

    public void getAllTiming() {
        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("AttachGraphAllUsers.mustache");
        mustache.execute(writer, new CollectStepsTime());
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = writer.toString();
        saveHtmlAttachAll(data);
        //DEBUG - saving to file
        try {
            try (PrintWriter out = new PrintWriter("target/graph.html")) {
                out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getAllTimingTable() {
        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("AttachTable.mustache");
        mustache.execute(writer, new CollectStepsTime());
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = writer.toString();
        saveHtmlAttachAllTable(data);
        //DEBUG - saving to file
        try {
            try (PrintWriter out = new PrintWriter("target/table.html")) {
                out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String camelCaseToNormal(String str) {
        String out = "";
        for (String w : str.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
            out += w.replace(w.charAt(0), Character.toUpperCase(w.charAt(0))) + " ";
        }
        return out.trim();
    }
}
