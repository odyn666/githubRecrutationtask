package com.github.odyn666.githubrecrutationtask.service;

import com.github.odyn666.githubrecrutationtask.dto.GitHubDTO;
import com.github.odyn666.githubrecrutationtask.model.Branch;
import com.github.odyn666.githubrecrutationtask.model.GitHubRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class GitHubService {
    @Value("${github.api.url.users}")
    private String githubUsersApiUrl;
    @Value("${github.api.url.repos}")
    private String githubReposApiUrl;
    private RestTemplate restTemplate = new RestTemplate();



    public List<GitHubDTO> getDTOs(String username) {

        String url = githubUsersApiUrl + username + "/repos?type=all";
        GitHubRepository[] repositories = restTemplate.getForObject(url, GitHubRepository[].class);
        if (repositories != null) {
            return Arrays.stream(repositories)
                    .filter(repository -> repository.getOwner().getLogin().equals(username))
                    .filter(r -> !r.isFork())
                    .map(r -> {
                        return GitHubDTO.builder()
                                .RepositoryName(r.getName())
                                .ownerLogin(r.getOwner().getLogin())
                                .branch(addBranchesToGithubRepository(r).getBranches())
                                .build();
                    })
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    private GitHubRepository addBranchesToGithubRepository(GitHubRepository repository) {
        List<Branch> branches = getBranches(repository.getOwner().getLogin(), repository.getName());
        repository.setBranches(branches);
        return repository;
    }

    public List<Branch> getBranches(String owner, String repoName) {
        String url = githubReposApiUrl + owner + "/" + repoName + "/branches";
        Branch[] branches = restTemplate.getForObject(url, Branch[].class);
        if (branches != null) {
            return Arrays.asList(branches);
        }
        return List.of();
    }

}
