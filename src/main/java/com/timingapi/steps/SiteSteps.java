package com.timingapi.steps;

import com.timingapi.base.BaseTest;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.timingapi.base.TimingAPI.getHar;
import static com.timingapi.base.TimingAPI.getPageLoadTime;

/**
 * Created by artyom on 19-Nov-16.
 */
public class SiteSteps extends BaseTest {

    @Step
    public void openMainPage(Integer inputNumber) {
        open("http://google.com/ncr");
        getPageLoadTime();
        //getHar();
    }

    @Step
    public void enterSearchWord(String word) {
        $(By.name("q")).val(word).pressEnter();
        getPageLoadTime();
        $$("#ires .g").shouldHave(size(10));
        //getHar();
    }

    @Step
    public void openFirstLink() {
        $(".r>a").click();
        getPageLoadTime();
        $$("#ires .g").shouldHave(size(10));
        //getHar();
    }
}
