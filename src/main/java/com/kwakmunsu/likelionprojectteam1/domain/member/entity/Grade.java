package com.kwakmunsu.likelionprojectteam1.domain.member.entity;

public enum Grade {

    SEEDLING_CHEF("새싹 요리사", 0),
    FLAME_CHEF("불꽃 요리사", 1500),
    LEGENDARY_CHEF("전설의 요리왕", 15000),
    ;

    private final String displayName;
    private final int value;

    Grade(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getValue() {
        return value;
    }

}