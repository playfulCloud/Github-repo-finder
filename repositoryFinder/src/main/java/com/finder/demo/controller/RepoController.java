package com.finder.demo.controller;

import com.finder.demo.exception.XmlFormatException;
import com.finder.demo.service.RepoService;
import com.finder.demo.service.RepoServiceImpl;
import com.finder.demo.util.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RepoController {

    private final RepoService repoService;


    @Autowired
    public RepoController(RepoServiceImpl repoService) {
        this.repoService = repoService;
    }


    @GetMapping(value = "find")
    public Repo[] findUserRepositoriesAndAssiociatedBranchesWithCommits(@RequestHeader("Accept") String acceptHeader, @RequestBody String username) {
        if (!MediaType.APPLICATION_JSON.isCompatibleWith(MediaType.valueOf(acceptHeader))) {
            throw new XmlFormatException("Unsupported format");
        }

        Repo[] repoWithoutForks = repoService.getNonForkUserRepositories(username);
        repoService.setBranchesForRepo(repoWithoutForks);

        return repoWithoutForks;
    }


    }


