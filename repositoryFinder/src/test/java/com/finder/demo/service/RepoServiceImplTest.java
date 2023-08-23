package com.finder.demo.service;

import com.finder.demo.util.Branch;
import com.finder.demo.util.Repo;
import com.finder.demo.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RepoServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    private RepoService repoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        repoService = new RepoServiceImpl(restTemplate);
    }

    @Test
    public void testGetUserRepositories() {
        String username = "testUser";
        Repo[] expectedRepos = {new Repo(), new Repo()};
        when(restTemplate.getForObject(anyString(), eq(Repo[].class))).thenReturn(expectedRepos);

        Repo[] result = repoService.getUserRepositories(username);

        assertArrayEquals(expectedRepos, result);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Repo[].class));
    }

    @Test
    public void testGetUserRepositories_UserNotFound() {
        String username = "nonExistentUser";
        when(restTemplate.getForObject(anyString(), eq(Repo[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(UserNotFoundException.class, () -> repoService.getUserRepositories(username));
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Repo[].class));
    }

    @Test
    public void testGetRepositoryBranches() {
        String owner = "testOwner";
        String repo = "testRepo";
        Branch[] expectedBranches = {new Branch(), new Branch()};
        when(restTemplate.getForObject(anyString(), eq(Branch[].class))).thenReturn(expectedBranches);

        Branch[] result = repoService.getRepositoryBranches(owner, repo);

        assertArrayEquals(expectedBranches, result);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(Branch[].class));
    }



}