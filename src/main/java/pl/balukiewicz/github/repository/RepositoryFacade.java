package pl.balukiewicz.github.repository;

import pl.balukiewicz.github.repository.dto.RepositoryDTO;

import java.util.Locale;

public class RepositoryFacade {

    private final RepositoryService repositoryService;
    private final RepositoryToDTOMapper repositoryToDTOMapper;

    public RepositoryFacade(RepositoryService repositoryService, RepositoryToDTOMapper repositoryToDTOMapper) {
        this.repositoryService = repositoryService;
        this.repositoryToDTOMapper = repositoryToDTOMapper;
    }

    public RepositoryDTO getRepository(String owner, String repoName, Locale locale) {
        return repositoryToDTOMapper.map(repositoryService.getRepository(owner, repoName), locale);
    }



}
