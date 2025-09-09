package com.example.carins.util;

import java.time.LocalDate;

public final class DateRange {
    private DateRange() {}

    public static final LocalDate MIN = LocalDate.of(1900, 1, 1);
    public static final LocalDate MAX = LocalDate.of(2100, 12, 31);

    public static boolean isSupported(LocalDate d) {
        return (d.compareTo(MIN) >= 0) && (d.compareTo(MAX) <= 0);
    }
}
