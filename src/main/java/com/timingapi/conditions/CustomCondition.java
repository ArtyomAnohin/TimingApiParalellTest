package com.timingapi.conditions;

import org.openqa.selenium.WebElement;

/**
 * Created by artyom
 */
public abstract class CustomCondition extends com.codeborne.selenide.Condition {

    public static final CustomCondition aboveZero = new CustomCondition("aboveZero") {

        @Override
        public boolean apply(WebElement element) {
            double value = Double.parseDouble(element.getText());
            return value > 0;
        }

        @Override
        public String actualValue(WebElement element) {
            return element.getText();
        }


        @Override
        public String toString() {

            return "above zero";

        }

    };

    public CustomCondition(String name) {
        super(name);
    }
}