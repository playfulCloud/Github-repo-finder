package com.finder.demo.service;

import com.finder.demo.util.Branch;
import com.finder.demo.util.Repo;

import java.util.List;


public interface RepoService {

    public Repo[] getUserRepositories(String username);

    public Branch[] getRepositoryBranches(String owner, String repo);

    public void setBranchesForRepo(List<Repo> repoList);


    public List<Repo> getNonForkUserRepositories(String username);


}
