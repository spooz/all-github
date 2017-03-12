package pl.balukiewicz.service;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import pl.balukiewicz.client.GithubClient;
import pl.balukiewicz.client.Repository;
import pl.balukiewicz.dto.RepositoryDTO;

import java.util.Locale;


@Service
public class GithubServiceImpl implements GithubService {

    private final GithubClient githubClient;

    @Autowired
    public GithubServiceImpl(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @Override
    public RepositoryDTO getRepositoryData(String owner, String repoName) {
        return new RepositoryDTO(githubClient.getRepository(owner, repoName), LocaleContextHolder.getLocale());
    }

}
