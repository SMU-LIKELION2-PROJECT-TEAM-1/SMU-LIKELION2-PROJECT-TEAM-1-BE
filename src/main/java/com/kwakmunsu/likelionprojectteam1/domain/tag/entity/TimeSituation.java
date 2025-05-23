package com.kwakmunsu.likelionprojectteam1.domain.tag.entity;

public enum TimeSituation {

    BREAKFAST ("아침"),
    LUNCH     ("점심"),
    DINNER    ("저녁"),
    LATE_NIGHT("야식"),
    SNACK     ("간식"),
    ;

    private final String value;

    TimeSituation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}