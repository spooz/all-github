package pl.balukiewicz.github.repository.client.exception;

import lombok.Getter;

@Getter
public class RepositoryServerException extends RuntimeException {

    private final int status;

    public RepositoryServerException(int status, String message) {
        super(message);
        this.status = status;
    }

}
