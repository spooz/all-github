package pl.balukiewicz.github.repository.client.exception;

import lombok.Getter;

@Getter
public class GithubClientException extends RuntimeException {

    private final int status;

    public GithubClientException(int status, String message) {
        super(message);
        this.status = status;
    }
}
