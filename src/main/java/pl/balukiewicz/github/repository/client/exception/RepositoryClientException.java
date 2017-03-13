package pl.balukiewicz.github.repository.client.exception;

import lombok.Getter;

@Getter
public class RepositoryClientException extends RuntimeException {

    private final int status;

    public RepositoryClientException(int status, String message) {
        super(message);
        this.status = status;
    }
}
