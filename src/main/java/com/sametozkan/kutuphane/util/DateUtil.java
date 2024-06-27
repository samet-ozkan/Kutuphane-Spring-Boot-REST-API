package com.sametozkan.kutuphane.util;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {

    private final DateTimeFormatter dateFormatter;

    public DateUtil() {
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public LocalDate convertToLocalDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, dateFormatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Geçersiz tarih formatı: " + dateStr);
        }
    }

    public String toString(LocalDate date) {
        return date.format(dateFormatter);
    }
}
