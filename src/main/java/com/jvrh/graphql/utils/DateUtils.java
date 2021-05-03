package com.jvrh.graphql.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final String PATTERN = "yyyyMMdd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static String toString(LocalDate date) {
        return FORMATTER.format(date);
    }

    public static LocalDate toDate(String date) {
        System.out.println(date);
        return LocalDate.parse(date, FORMATTER);
    }
}
