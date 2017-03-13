package pl.balukiewicz.github.repository;

import pl.balukiewicz.github.repository.client.RepositoryClient;
import pl.balukiewicz.github.repository.client.Repository;

class RepositoryService {

    private final RepositoryClient repositoryClient;

    RepositoryService(RepositoryClient repositoryClient) {
        this.repositoryClient = repositoryClient;
    }

    Repository getRepositoryData(String owner, String repoName) {
        return repositoryClient.getRepository(owner, repoName);
    }

}
