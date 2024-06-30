package com.sametozkan.kutuphane.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {

    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter dateTimeFormatter;

    public DateUtil() {
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
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

    public LocalDateTime convertToLocalDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Geçersiz tarih ve saat formatı: " + dateTimeStr);
        }
    }

    public String toString(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }
}
