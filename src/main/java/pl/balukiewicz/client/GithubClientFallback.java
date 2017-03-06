package pl.balukiewicz.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import pl.balukiewicz.model.RepositoryData;

/**
 * Created by Bartek on 06.03.2017.
 */
@Component
public class GithubClientFallback implements GithubClient{

    @Override
    public RepositoryData getRepositoryData(@PathVariable(name = "owner") String owner,
                                            @PathVariable(name = "repository-name") String repoName) {
        return RepositoryData.getEmptyData();
    }
}
