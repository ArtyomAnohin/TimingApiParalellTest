package com.timingapi.autotest;

import com.codeborne.selenide.WebDriverRunner;
import com.timingapi.base.BaseTest;
import com.timingapi.base.Browsers;
import com.timingapi.steps.SiteSteps;
import org.testng.annotations.Test;

import java.io.IOException;


/**
 * Created by artyom
 */
public class LoadTest extends BaseTest {

    @Test(dataProvider = "test1")
    public void mainTest(Integer inputNumber, String username, String password) throws IOException {
        WebDriverRunner.setWebDriver(setUp(Browsers.Chrome));
        SiteSteps siteSteps = new SiteSteps();
        siteSteps.openMainPage(inputNumber);
        siteSteps.enterSearchWord("selenide", username, password);
        siteSteps.openFirstLink();
        collectTimeEachUser(inputNumber);
    }

    @Test(dependsOnMethods = {"mainTest"}, alwaysRun = true)
    public void collectAllUsers() throws IOException {
        getAllTiming();
        getAllTimingTable();
    }
}
