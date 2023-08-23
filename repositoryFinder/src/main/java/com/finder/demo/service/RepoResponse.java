package com.finder.demo.service;

import com.finder.demo.Repo;
import lombok.Data;

import java.util.List;

@Data
public class RepoResponse {

    List<Repo> repoList;

}
