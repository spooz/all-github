package pl.balukiewicz.github.repository.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//using Feign custom Error Decoder instead of Hystrix fallback in order to properly handle Github's response
@FeignClient(name = "github", url = "${github.api.url}", configuration = RepositoryClientConfig.class)
public interface RepositoryClient {

    @RequestMapping(value = "/repos/{owner}/{repository-name}", method = RequestMethod.GET)
    Repository getRepository(@PathVariable(name = "owner") String owner,
                                 @PathVariable(name = "repository-name") String repoName);
}
