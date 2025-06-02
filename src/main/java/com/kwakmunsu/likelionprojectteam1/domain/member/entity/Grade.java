package com.kwakmunsu.likelionprojectteam1.domain.member.entity;

public enum Grade {

    SEEDLING_CHEF(0),
    FLAME_CHEF(1500),
    LEGENDARY_CHEF(15000),
    ;

    private final int value;

    Grade(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Grade getGradeByPoint(int point) {
        Grade result = SEEDLING_CHEF;

        for (Grade grade : values()) {
            if (point >= grade.value) {
                result = grade;
            }
        }

        return result;
    }

}