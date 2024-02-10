package com.github.odyn666.githubrecrutationtask.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException   {
   private final int status;
   private final String message;

    public UserNotFoundException(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
