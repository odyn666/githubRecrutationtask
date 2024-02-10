package com.github.odyn666.githubrecrutationtask.exception;

import lombok.Getter;

@Getter
public class BadHeaderException {
    private final int status;
    private final String message;

    public BadHeaderException(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
