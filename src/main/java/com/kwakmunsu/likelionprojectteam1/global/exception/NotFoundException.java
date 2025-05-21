package com.kwakmunsu.likelionprojectteam1.global.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends RootException {

    public NotFoundException(String message) {
        super(NOT_FOUND, message);
    }

}