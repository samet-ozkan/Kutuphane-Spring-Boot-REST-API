package com.sametozkan.kutuphane.util;

import com.sametozkan.kutuphane.entity.model.Account;
import lombok.Getter;

@Getter
public enum AccountType {
    KULLANICI("kullanici"),
    KUTUPHANE("kutuphane"),
    ADMIN("admin");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    public static AccountType fromValue(String value) {
        for (AccountType accountType : AccountType.values()) {
            if (accountType.value.equalsIgnoreCase(value)) {
                return accountType;
            }
        }
        throw new IllegalArgumentException("Invalid Account Type value: " + value);
    }
}
