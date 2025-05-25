package com.kwakmunsu.likelionprojectteam1.domain.member.entity;

import lombok.Getter;

@Getter
public enum SocialType {

    GOOGLE,
    KAKAO,
    ;

    public static SocialType from(String value) {
        return SocialType.valueOf(value.toUpperCase());
    }

}