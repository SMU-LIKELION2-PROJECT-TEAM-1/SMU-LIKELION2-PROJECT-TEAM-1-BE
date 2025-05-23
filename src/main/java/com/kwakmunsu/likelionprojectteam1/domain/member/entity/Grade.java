package com.kwakmunsu.likelionprojectteam1.domain.member.entity;

public enum Grade {

    BRONZE(0),
    SILVER(5000),
    GOLD(15000),
    ;
    private final int value;

    Grade(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}