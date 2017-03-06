package pl.balukiewicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.balukiewicz.model.RepositoryData;
import pl.balukiewicz.service.GithubService;

/**
 * Created by Bartek on 06.03.2017.
 */
@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private GithubService githubService;

    @Autowired
    public ApiController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/repositories/{owner}/{repository-name}")
    public RepositoryData getRepositoryData(@PathVariable(name = "owner") String owner, @PathVariable(name="repository-name") String repoName) {
        return githubService.getRepositoryData(owner, repoName);
    }

}
