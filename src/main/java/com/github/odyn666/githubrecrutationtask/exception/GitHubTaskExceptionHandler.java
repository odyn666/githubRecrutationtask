package com.github.odyn666.githubrecrutationtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GitHubTaskExceptionHandler {


    @ExceptionHandler(BadHeaderException.class)
    public ResponseEntity<String>handleBadHeaderException(BadHeaderException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStatus()+" "+e.getMessage());
    }
}

