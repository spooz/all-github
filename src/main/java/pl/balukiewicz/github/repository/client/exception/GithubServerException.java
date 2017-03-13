package pl.balukiewicz.github.repository.client.exception;

import lombok.Getter;

@Getter
public class GithubServerException extends RuntimeException {

    private final int status;

    public GithubServerException(int status, String message) {
        super(message);
        this.status = status;
    }

}
