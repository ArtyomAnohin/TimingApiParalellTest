package com.timingapi.steps;

import com.timingapi.base.BaseTest;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.timingapi.base.TimingAPI.getPageLoadTime;

/**
 * Created by artyom
 */
public class SiteSteps extends BaseTest {

    @Step
    @Title("Open main page")
    public void openMainPage(Integer inputNumber) {
        open("http://google.com/ncr");
        getPageLoadTime();
    }

    @Step
    @Title("Enter search word: {0}, additional {1} {2}")
    public void enterSearchWord(String word, String username, String password) {
        $(By.name("q")).val(word).pressEnter();
        getPageLoadTime();
        $$("#ires .g").shouldHave(size(10));
    }

    @Step
    @Title("Open first link")
    public void openFirstLink() {
        $(".r>a").click();
        getPageLoadTime();
        //$$("#ires .g").shouldHave(size(10));
    }
}
