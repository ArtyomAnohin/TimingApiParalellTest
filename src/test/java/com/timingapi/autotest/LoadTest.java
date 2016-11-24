package com.timingapi.autotest;

import com.codeborne.selenide.WebDriverRunner;
import com.timingapi.base.BaseTest;
import com.timingapi.base.Browsers;
import com.timingapi.steps.SiteSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Created by User on 20-Nov-16.
 */
public class LoadTest extends BaseTest{
    @Test(dataProvider = "test1")
    public void test01(Integer inputNumber) {
        WebDriverRunner.setWebDriver(setUp(Browsers.Chrome));
        SiteSteps siteSteps = new SiteSteps();
        siteSteps.openMainPage(inputNumber);
        siteSteps.enterSearchWord("selenide" + inputNumber);
        siteSteps.openFirstLink();
    }

}
