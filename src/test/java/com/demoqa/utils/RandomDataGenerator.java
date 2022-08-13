package com.demoqa.utils;

import java.time.format.TextStyle;
import java.util.Random;
import java.util.OptionalInt;
import java.util.Locale;
import java.time.Month;




public class RandomDataGenerator {

    public static   String generateRandomMonth() {
        Random random = new Random();
        OptionalInt randomNumberMonth = random.ints(0, 12).findFirst();
        return Month.of(randomNumberMonth.getAsInt()).getDisplayName(TextStyle.FULL, Locale.US);

    }
}
