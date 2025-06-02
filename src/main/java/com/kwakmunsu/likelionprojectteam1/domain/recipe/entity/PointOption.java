package com.kwakmunsu.likelionprojectteam1.domain.recipe.entity;

public enum PointOption {
    LIKE(200),
    UN_LIKE(-200),
    COMMENT(200),
    UN_COMMENT(-200),
    UN_VOTE(-100),
    CHALLENGE(1000),
    WEEKLY(2000),
    ;

    private final int value;

    PointOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}