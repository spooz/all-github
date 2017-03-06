package pl.balukiewicz.service;

import pl.balukiewicz.model.RepositoryData;

/**
 * Created by Bartek on 06.03.2017.
 */
public interface GithubService {

    RepositoryData getRepositoryData(String owner, String repoName);

}
