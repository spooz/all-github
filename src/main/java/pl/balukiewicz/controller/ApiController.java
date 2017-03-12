package pl.balukiewicz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.balukiewicz.dto.RepositoryDTO;
import pl.balukiewicz.service.GithubServiceFacade;

import java.util.Locale;


@RestController
public class ApiController {

    private final GithubServiceFacade githubService;

    public ApiController(GithubServiceFacade githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/repositories/{owner}/{repository-name}")
    public ResponseEntity<RepositoryDTO> getRepositoryData(@PathVariable(name = "owner") String owner,
                                                           @PathVariable(name="repository-name") String repoName,
                                                           Locale locale) {
        return ResponseEntity.ok(githubService.getRepositoryData(owner, repoName, locale));
    }


}
