package com.finder.demo.controller;

import com.finder.demo.exception.XmlFormatException;
import com.finder.demo.service.RepoService;

import com.finder.demo.util.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RepoController {
    private final RepoService repoService;

    @Autowired
    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @GetMapping(value = "find")
    public List<Repo> findUserRepositoriesAndAssiociatedBranchesWithCommits(@RequestHeader("Accept") String acceptHeader, @RequestBody String username) {
        try {
            List<Repo> repoWithoutForks = repoService.getNonForkUserRepositories(username);
            repoService.setBranchesForRepo(repoWithoutForks);
            return repoWithoutForks;
        } catch (WebClientResponseException e) {
            throw new XmlFormatException("Unsupported format");
        }

    }


}


