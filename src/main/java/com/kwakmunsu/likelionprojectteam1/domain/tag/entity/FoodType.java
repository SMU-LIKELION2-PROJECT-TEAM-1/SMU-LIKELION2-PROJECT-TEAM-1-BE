package com.kwakmunsu.likelionprojectteam1.domain.tag.entity;

public enum FoodType {

    KOREAN  ("한식"),
    WESTERN ("양식"),
    JAPANESE("일식"),
    CHINESE ("중식"),
    ;

    private final String value;

    FoodType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}