package com.finder.demo.controller;

import com.finder.demo.Repo;
import com.finder.demo.exception.XmlFormatException;
import com.finder.demo.service.RepoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RepoController {

    private RepoServiceImpl repoService;


    @Autowired
    public RepoController(RepoServiceImpl repoService) {
        this.repoService = repoService;
    }


    @GetMapping("find")
    public Repo[] findUserRepositoriesAndAssiociatedBranchesWithCommits(@RequestHeader("Accept") String acceptHeader, @RequestBody String username) {

//        checkFormat(acceptHeader);

        return repoService.getUserRepositories(username);
    }


    public boolean checkFormat(@RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE)) {
            return true;
        }else{
            throw new XmlFormatException("Unsupported format");
        }
    }





}
