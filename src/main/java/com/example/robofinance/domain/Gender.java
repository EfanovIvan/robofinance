package com.example.robofinance.domain;

public enum Gender {
    MALE, FEMALE;

    public String toDbValue() {
        return this.name().toLowerCase();
    }

    public static Gender from(String gender) {

        return Gender.valueOf(gender.toUpperCase());
    }
}
