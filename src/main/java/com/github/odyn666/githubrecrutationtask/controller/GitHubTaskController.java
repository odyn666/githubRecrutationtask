package com.github.odyn666.githubrecrutationtask.controller;

import com.github.odyn666.githubrecrutationtask.exception.BadHeaderException;

import com.github.odyn666.githubrecrutationtask.dto.GitHubDTO;
import com.github.odyn666.githubrecrutationtask.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            throw new BadHeaderException(HttpStatus.BAD_REQUEST.value(), "Invalid Accept Header");
        }


        List<GitHubDTO> repositories = gitHubService.getDTOs(username);

        return ResponseEntity.ok(repositories);
    }


}
