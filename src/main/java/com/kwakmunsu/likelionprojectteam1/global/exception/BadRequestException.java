package com.kwakmunsu.likelionprojectteam1.global.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends RootException {

    public BadRequestException(String message) {
        super(BAD_REQUEST, message);
    }

}