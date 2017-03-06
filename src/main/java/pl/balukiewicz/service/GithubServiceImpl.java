package pl.balukiewicz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.balukiewicz.client.GithubClient;
import pl.balukiewicz.model.RepositoryData;

/**
 * Created by Bartek on 06.03.2017.
 */
@Service
public class GithubServiceImpl implements GithubService {

    private GithubClient githubClient;

    @Autowired
    public GithubServiceImpl(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @Override
    public RepositoryData getRepositoryData(String owner, String repoName) {
        return githubClient.getRepositoryData(owner, repoName);
    }
}
