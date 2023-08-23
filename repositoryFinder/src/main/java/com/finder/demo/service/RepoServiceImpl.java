package com.finder.demo.service;


import com.finder.demo.Branch;
import com.finder.demo.Repo;
import com.finder.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RepoServiceImpl {
    private final String GITHUB_API_BASE_URL = "https://api.github.com";
    private final RestTemplate restTemplate;

    @Autowired
    public RepoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public Repo[] getUserRepositories(String username) {

        isUserExists(username);

        String url = GITHUB_API_BASE_URL + "/users/" + username + "/repos";
        Repo[] repoArray = restTemplate.getForObject(url, Repo[].class);

        setBranchesForRepo(repoArray);
        return repoArray;
    }

    public Branch[] getRepositoryBranches(String owner, String repo) {
        String url = GITHUB_API_BASE_URL + "/repos/" + owner + "/" + repo + "/branches";

        return restTemplate.getForObject(url, Branch[].class);
    }

    public void setBranchesForRepo(Repo[] repoArray){
        for(Repo repo : repoArray){
            repo.setBranches(getRepositoryBranches(repo.getOwner().getLogin(),repo.getName()));
        }
    }

    public void isUserExists(String username) {
        String url = GITHUB_API_BASE_URL + "/users/" + username;

        try {
            restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException("User not found");
            }
            throw e;
        }
    }


}
