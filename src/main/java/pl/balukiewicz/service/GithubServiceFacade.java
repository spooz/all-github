package pl.balukiewicz.service;

import org.springframework.stereotype.Component;
import pl.balukiewicz.dto.RepositoryDTO;

import java.util.Locale;

@Component
public class GithubServiceFacade {

    private final GithubService githubService;
    private final RepositoryToDTOMapper repositoryToDTOMapper;

    public GithubServiceFacade(GithubService githubService, RepositoryToDTOMapper repositoryToDTOMapper) {
        this.githubService = githubService;
        this.repositoryToDTOMapper = repositoryToDTOMapper;
    }

    public RepositoryDTO getRepositoryData(String owner, String repoName, Locale locale) {
        return repositoryToDTOMapper.map(githubService.getRepositoryData(owner, repoName), locale);
    }



}
