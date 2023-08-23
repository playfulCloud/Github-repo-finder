package com.finder.demo.service;

import com.finder.demo.util.Branch;
import com.finder.demo.util.Repo;
import org.springframework.stereotype.Service;


public interface RepoService {

    public Repo[] getUserRepositories(String username);
    public Branch[] getRepositoryBranches(String owner, String repo);

    public void setBranchesForRepo(Repo[] repoArray);



    public Repo[] getNonForkUserRepositories(String username);





}
