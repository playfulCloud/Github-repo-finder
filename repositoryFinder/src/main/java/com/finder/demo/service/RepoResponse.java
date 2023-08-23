package com.finder.demo.service;

import com.finder.demo.util.Repo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RepoResponse {
    private final List<Repo> repoList;
}
