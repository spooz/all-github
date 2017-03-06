package pl.balukiewicz.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.balukiewicz.model.RepositoryData;

/**
 * Created by Bartek on 06.03.2017.
 */
@FeignClient(name = "github", url = "${github.api.url}", fallback = GithubClientFallback.class)
public interface GithubClient {

    @RequestMapping(value = "/repos/{owner}/{repository-name}", method = RequestMethod.GET)
    RepositoryData getRepositoryData(@PathVariable(name = "owner") String owner,
                                     @PathVariable(name = "repository-name") String repoName);
}
