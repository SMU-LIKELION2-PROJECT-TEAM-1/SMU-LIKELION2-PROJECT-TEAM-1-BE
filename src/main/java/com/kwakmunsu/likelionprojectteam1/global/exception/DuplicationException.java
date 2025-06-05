package com.kwakmunsu.likelionprojectteam1.global.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

public class DuplicationException extends RootException {

    public DuplicationException(String message) {
        super(CONFLICT, message);
    }

}