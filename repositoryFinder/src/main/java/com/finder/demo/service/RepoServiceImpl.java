package com.finder.demo.service;


import com.finder.demo.exception.UserNotFoundException;
import com.finder.demo.util.Branch;
import com.finder.demo.util.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class RepoServiceImpl implements RepoService {
    private final String GITHUB_API_BASE_URL = "https://api.github.com";
    private final WebClient.Builder webClient;

    @Autowired
    public RepoServiceImpl(WebClient.Builder webClient) {
        this.webClient = webClient;
    }

    public Repo[] getUserRepositories(String username) {
        try {
            return webClient.build()
                    .get()
                    .uri(GITHUB_API_BASE_URL + "/users/" + username + "/repos")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .bodyToMono(Repo[].class)
                    .block();

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("User not found");
            }
            throw e;
        }
    }

    public Branch[] getRepositoryBranches(String owner, String repo) {
        return webClient.build()
                .get()
                .uri(GITHUB_API_BASE_URL + "/repos/" + owner + "/" + repo + "/branches")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Branch[].class)
                .block();
    }

    public void setBranchesForRepo(List<Repo> repoList) {
        repoList.forEach(repo -> repo.
                setBranches(
                        getRepositoryBranches(
                                repo.getOwner().getLogin(),
                                repo.getName()))
        );
    }

    public List<Repo> getNonForkUserRepositories(String username) {
        Repo[] repositories = getUserRepositories(username);
        return Arrays.stream(repositories)
                .filter(repository -> !repository.isFork()).toList();
    }


}
