package com.github.odyn666.githubrecrutationtask.mapper;

import com.github.odyn666.githubrecrutationtask.model.Branch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GitHubDTO {
    String RepositoryName;
    String ownerLogin;
    List<Branch> branch;
}
