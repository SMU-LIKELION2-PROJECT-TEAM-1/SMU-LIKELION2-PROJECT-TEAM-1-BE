package com.kwakmunsu.likelionprojectteam1.global.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalServerException extends RootException {

    public InternalServerException(String message) {
        super(INTERNAL_SERVER_ERROR, message);
    }

}