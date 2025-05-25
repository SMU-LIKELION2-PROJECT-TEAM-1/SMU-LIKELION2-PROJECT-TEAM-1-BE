package com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.common;

import lombok.Getter;

@Getter
public enum TokenType {

    AUTHORIZATION_HEADER("Authorization"),
    BEARER_PREFIX("Bearer "),
    ACCESS("accessToken"),
    REFRESH("refreshToken"),
    TEMPORARY("temporaryToken")
    ;

    private final String value;

    TokenType(String value) {
        this.value = value;
    }

}