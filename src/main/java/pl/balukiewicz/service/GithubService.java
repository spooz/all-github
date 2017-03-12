package pl.balukiewicz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.balukiewicz.client.GithubClient;
import pl.balukiewicz.client.Repository;


@Service
public class GithubService {

    private final GithubClient githubClient;

    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public Repository getRepositoryData(String owner, String repoName) {
        return githubClient.getRepository(owner, repoName);
    }

}
