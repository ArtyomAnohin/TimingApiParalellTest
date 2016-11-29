package com.timingapi.base;

import java.util.List;

/**
 * Created by artyom
 */
public class CollectStepsTime extends BaseTest {

    public static List<Item> items() {
        listUsersTiming.sort((a, b) -> a.name.compareTo(b.name));
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
        StepTime(String description, PageLoadTiming time) {
            this.description = description;
            this.time = time;
        }

        String description;
        PageLoadTiming time;
    }

}
