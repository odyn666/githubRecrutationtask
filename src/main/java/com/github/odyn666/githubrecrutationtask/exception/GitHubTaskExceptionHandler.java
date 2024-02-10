package com.github.odyn666.githubrecrutationtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GitHubTaskExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String>handleUserNotFoundException(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getStatus()+" "+e.getMessage());
    }

    @ExceptionHandler(BadHeaderException.class)
    public ResponseEntity<String>handleBadHeaderException(BadHeaderException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getStatus()+" "+e.getMessage());
    }
}

