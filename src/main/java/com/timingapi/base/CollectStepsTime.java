package com.timingapi.base;

import java.util.List;

/**
 * Created by User on 27-Nov-16.
 */
public class CollectStepsTime extends BaseTest{


    public static List<Item> items() {
        return listUsersTiming;
    }

    static class Item {
        Item(String name, List<StepTime> stepTimes) {
            this.name = name;
            this.stepTimes = stepTimes;
        }

        String name;
        List<StepTime> stepTimes;

    }

    static class StepTime {
        StepTime(String description, Long time) {
            this.description = description;
            this.time = time;
        }

        String description;
        Long time;
    }

}
