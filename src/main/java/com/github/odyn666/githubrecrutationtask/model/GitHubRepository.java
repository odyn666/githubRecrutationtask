package com.github.odyn666.githubrecrutationtask.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GitHubRepository {
    String name;
    boolean fork;
    List<Branch>branches;
    GitHubUser owner;

}
