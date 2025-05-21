package com.kwakmunsu.likelionprojectteam1.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class RootException extends RuntimeException {

    private final HttpStatus httpStatus;

    protected RootException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

}