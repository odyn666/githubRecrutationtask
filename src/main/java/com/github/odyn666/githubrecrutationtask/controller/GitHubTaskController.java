package com.github.odyn666.githubrecrutationtask.controller;

import com.github.odyn666.githubrecrutationtask.exception.BadHeaderException;

import com.github.odyn666.githubrecrutationtask.dto.GitHubDTO;
import com.github.odyn666.githubrecrutationtask.exception.UserNotFoundException;
import com.github.odyn666.githubrecrutationtask.model.GitHubUser;
import com.github.odyn666.githubrecrutationtask.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GitHubTaskController {
    private final GitHubService gitHubService;



    @GetMapping("/github/repositories")
    public ResponseEntity<?> getGitHubRepositories(
            @RequestParam String username,
            @RequestHeader("Accept") String acceptHeader
    ) {

        if (!acceptHeader.equals("application/json")) {
            return new ResponseEntity<>(new BadHeaderException(HttpStatus.NOT_FOUND.value(), "INVALID ACCEPT HEADER"),HttpStatus.NOT_FOUND);
        }

        try {
            GitHubUser user = gitHubService.getGitHubUser(username);

        } catch (HttpClientErrorException.NotFound notFoundException) {
            return new ResponseEntity<>(new UserNotFoundException(HttpStatus.NOT_FOUND.value(), "USER NOT FOUND"), HttpStatus.NOT_FOUND);

        }

        List<GitHubDTO> repositories = gitHubService.getDTOs(username);

        return ResponseEntity.ok(repositories);
    }


}
