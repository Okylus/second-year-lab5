package ru.arkhipov.MySecondTestAppSpringBoot.util;

import java.text.SimpleDateFormat;

public class DateTimeUtil {

    public static SimpleDateFormat getDateTimeFormatter() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}