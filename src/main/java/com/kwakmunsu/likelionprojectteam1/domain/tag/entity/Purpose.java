package com.kwakmunsu.likelionprojectteam1.domain.tag.entity;

public enum Purpose {

    DIET     ("다이어트"),
    BULK_UP  ("벌크업"),
    HEALTHY  ("건강식"),
    HANGOVER ("해장"),
    SOLO_MEAL("혼밥"),
    ;

    private final String value;

    Purpose(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
