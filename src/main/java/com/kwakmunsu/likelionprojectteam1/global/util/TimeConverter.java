package com.kwakmunsu.likelionprojectteam1.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimeConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yy.MM.dd").withLocale(Locale.KOREAN);

    public static String datetimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_FORMATTER);
    }

}