package com.timingapi.steps;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.IOException;

/**
 * Created by artyom
 */
public class OnFailure extends TestListenerAdapter {

    @Step("Failure screenshot")
    @Override
    public void onTestFailure(ITestResult tr) {
        try {
            screenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.getLastScreenshot();
        return Files.toByteArray(screenshot);
    }
}