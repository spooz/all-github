package pl.balukiewicz.github;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.balukiewicz.github.repository.client.exception.GithubClientException;
import pl.balukiewicz.github.repository.client.exception.GithubServerException;

@ControllerAdvice
class ApiErrorAdvice {

    @ExceptionHandler(GithubClientException.class)
    ResponseEntity<ApiError> handleGithubClientException(GithubClientException exception) {
        return new ResponseEntity<>(new ApiError("Github client error:" + exception.getMessage()),
                HttpStatus.valueOf(exception.getStatus()));
    }

    @ExceptionHandler(GithubServerException.class)
    ResponseEntity<ApiError> handleGithubServerExcetpion(GithubServerException exception) {
        return new ResponseEntity<>(new ApiError("Github internal error:" + exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiError> handleDefaultException(Exception exception) {
        return new ResponseEntity<>(new ApiError("Internal error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
