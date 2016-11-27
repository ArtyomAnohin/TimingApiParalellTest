package com.timingapi.autotest;

import com.codeborne.selenide.WebDriverRunner;
import com.timingapi.base.BaseTest;
import com.timingapi.base.Browsers;
import com.timingapi.steps.SiteSteps;
import org.testng.annotations.Test;


/**
 * Created by artyom on 20-Nov-16.
 */
public class LoadTest extends BaseTest{
    @Test(dataProvider = "test1")
    public void test01(Integer inputNumber) {
        WebDriverRunner.setWebDriver(setUp(Browsers.Chrome));
        SiteSteps siteSteps = new SiteSteps();
        siteSteps.openMainPage(inputNumber);
        siteSteps.enterSearchWord("selenide");
        siteSteps.openFirstLink();
        System.out.println(stepsTiming.toString());

    }

}
