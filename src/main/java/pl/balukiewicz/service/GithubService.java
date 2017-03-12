package pl.balukiewicz.service;

import pl.balukiewicz.dto.RepositoryDTO;

public interface GithubService {

    RepositoryDTO getRepositoryData(String owner, String repoName);

}
