package pl.balukiewicz.github;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.balukiewicz.github.repository.dto.RepositoryDTO;
import pl.balukiewicz.github.repository.RepositoryFacade;

import java.util.Locale;


@RestController
class ApiEndpoint {

    private final RepositoryFacade repositoryFacade;

    ApiEndpoint(RepositoryFacade repositoryFacade) {
        this.repositoryFacade = repositoryFacade;
    }

    @GetMapping("/repositories/{owner}/{repository-name}")
    ResponseEntity<RepositoryDTO> getRepositoryData(@PathVariable(name = "owner") String owner,
                                                           @PathVariable(name="repository-name") String repoName,
                                                           Locale locale) {
        return ResponseEntity.ok(repositoryFacade.getRepositoryData(owner, repoName, locale));
    }


}
