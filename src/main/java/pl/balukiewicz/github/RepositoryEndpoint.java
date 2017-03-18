package pl.balukiewicz.github;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.balukiewicz.github.repository.client.exception.RepositoryClientException;
import pl.balukiewicz.github.repository.client.exception.RepositoryServerException;
import pl.balukiewicz.github.repository.dto.RepositoryDTO;
import pl.balukiewicz.github.repository.RepositoryFacade;

import java.util.Locale;


@RestController
class RepositoryEndpoint {

    private final RepositoryFacade repositoryFacade;

    RepositoryEndpoint(RepositoryFacade repositoryFacade) {
        this.repositoryFacade = repositoryFacade;
    }

    @GetMapping("/repositories/{owner}/{repository-name}")
    ResponseEntity<RepositoryDTO> getRepositoryData(@PathVariable(name = "owner") String owner,
                                                           @PathVariable(name="repository-name") String repoName,
                                                           Locale locale) {
        return ResponseEntity.ok(repositoryFacade.getRepository(owner, repoName, locale));
    }

    @ExceptionHandler(RepositoryClientException.class)
    ResponseEntity<RepositoryEndpointError> handleGithubClientException(RepositoryClientException exception) {
        return new ResponseEntity<>(new RepositoryEndpointError("Github client error:" + exception.getMessage()),
                HttpStatus.valueOf(exception.getStatus()));
    }

    @ExceptionHandler(RepositoryServerException.class)
    ResponseEntity<RepositoryEndpointError> handleGithubServerExcetpion(RepositoryServerException exception) {
        return new ResponseEntity<>(new RepositoryEndpointError("Github internal error:" + exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<RepositoryEndpointError> handleDefaultException(Exception exception) {
        return new ResponseEntity<>(new RepositoryEndpointError("Internal error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

@Getter
@AllArgsConstructor
class RepositoryEndpointError {
    private final String message;
}

