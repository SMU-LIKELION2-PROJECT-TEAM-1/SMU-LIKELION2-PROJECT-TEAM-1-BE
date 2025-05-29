package com.kwakmunsu.likelionprojectteam1.domain.tag.entity;

public enum Purpose {

    DIET     ("다이어트 식단"),
    BULK_UP  ("벌크업 식단"),
    HEALTHY  ("건강식"),
    HANGOVER ("해장용"),
    SOLO_MEAL("혼밥용"),
    ;

    private final String value;

    Purpose(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
