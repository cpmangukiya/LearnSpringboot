package com.example.LearnSpringboot.events;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GithubProjectRepo extends PagingAndSortingRepository<GithubProject, Long> {

    GithubProject findByRepoName(String repoName);

}
