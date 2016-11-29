package com.timingapi.base;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.gson.Gson;
import org.openqa.selenium.JavascriptExecutor;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by artyom
 */
public class TimingAPI extends BaseTest {
    public static void getPageLoadTime() {
        String pageLoadTimingJson = ((JavascriptExecutor) getWebDriver()).executeScript("return JSON.stringify(window.performance.timing);").toString().replace("\"", "'");
        //String pageResourcesJson =((JavascriptExecutor) getWebDriver()).executeScript("return JSON.stringify(window.performance.getEntries());").toString().replace("\"","'");

        PageLoadTiming pageLoadTiming = new Gson().fromJson(pageLoadTimingJson, PageLoadTiming.class);

        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("AttachGraph.mustache");
        mustache.execute(writer, pageLoadTiming);
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String data = writer.toString();
        saveHtmlAttach(pageLoadTiming.getPageLoadTime(), data);
        //collectPerUser data
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        String methodName = stacktrace[2].getMethodName();
        collectPerUser(methodName, pageLoadTiming);
    }

    @Attachment(value = "Page load time {0} ms", type = "text/html")
    public static byte[] saveHtmlAttach(Long pageLoadTime, String attachName) {
        return attachName.getBytes();
    }

    @Attachment(value = "Result Graph", type = "text/html")
    public static byte[] saveHtmlAttachAll(String attachName) {
        return attachName.getBytes();
    }

    @Attachment(value = "Result Table", type = "text/html")
    public static byte[] saveHtmlAttachAllTable(String attachName) {
        return attachName.getBytes();
    }
}
