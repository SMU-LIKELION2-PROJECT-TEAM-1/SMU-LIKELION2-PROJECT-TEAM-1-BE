package com.kwakmunsu.likelionprojectteam1.global.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class UnAuthenticationException extends RootException {

    public UnAuthenticationException(String message) {
        super(UNAUTHORIZED, message);
    }

}