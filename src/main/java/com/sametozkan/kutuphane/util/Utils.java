package com.sametozkan.kutuphane.util;

import java.time.LocalDateTime;

public final class Utils {

    public static String getFirstName(String fullName) {
        int index = fullName.lastIndexOf(" ");
        if (index > -1) {
            return fullName.substring(0, index);
        }
        return fullName;
    }

    public static String getLastName(String fullName) {
        int index = fullName.lastIndexOf(" ");
        if (index > -1) {
            return fullName.substring(index + 1, fullName.length());
        }
        return "";
    }
}
