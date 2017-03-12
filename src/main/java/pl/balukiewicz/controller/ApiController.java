package pl.balukiewicz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.balukiewicz.client.Repository;
import pl.balukiewicz.dto.RepositoryDTO;
import pl.balukiewicz.service.GithubService;


@RestController
public class ApiController {

    private final GithubService githubService;

    @Autowired
    public ApiController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/repositories/{owner}/{repository-name}")
    public ResponseEntity<RepositoryDTO> getRepositoryData(@PathVariable(name = "owner") String owner,
                                                           @PathVariable(name="repository-name") String repoName) {
        return ResponseEntity.ok(githubService.getRepositoryData(owner, repoName));
    }


}
