package com.timingapi.autotest;

import com.codeborne.selenide.WebDriverRunner;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.timingapi.base.BaseTest;
import com.timingapi.base.Browsers;
import com.timingapi.base.CollectStepsTime;
import com.timingapi.steps.SiteSteps;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by artyom on 20-Nov-16.
 */
public class LoadTest extends BaseTest{


    @Test(dataProvider = "test1")
    public void mainTest(Integer inputNumber) throws IOException {
        WebDriverRunner.setWebDriver(setUp(Browsers.Chrome));
        SiteSteps siteSteps = new SiteSteps();
        siteSteps.openMainPage(inputNumber);
        siteSteps.enterSearchWord("selenide");
        siteSteps.openFirstLink();
        System.out.println(stepsTiming.toString());
        collectTimeEachUser(inputNumber);

    }

    @Test(dependsOnMethods = {"mainTest"},alwaysRun = true)
    public void collectAllUsers() throws IOException {

//        MustacheFactory mf = new DefaultMustacheFactory();
//        Mustache mustache = mf.compile("AttachGraphAllUsers.mustache");
//        mustache.execute(new PrintWriter(System.out), new CollectStepsTime()).flush();
        getAllTiming();
    }

}
