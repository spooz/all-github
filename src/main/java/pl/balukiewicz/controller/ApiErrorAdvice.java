package pl.balukiewicz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.balukiewicz.client.GithubClientException;
import pl.balukiewicz.client.GithubServerException;

@ControllerAdvice
public class ApiErrorAdvice {

    @ExceptionHandler(GithubClientException.class)
    public ResponseEntity<ApiError> handleGithubClientException(GithubClientException exception) {
        return new ResponseEntity<>(new ApiError("Github client error:" + exception.getMessage()),
                HttpStatus.valueOf(exception.getStatus()));
    }

    @ExceptionHandler(GithubServerException.class)
    public ResponseEntity<ApiError> handleGithubServerExcetpion(GithubServerException exception) {
        return new ResponseEntity<>(new ApiError("Github internal error:" + exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleDefaultException(Exception exception) {
        return new ResponseEntity<>(new ApiError("Internal error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
