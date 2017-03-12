package pl.balukiewicz.client;

import lombok.Getter;

@Getter
public class GithubClientException extends RuntimeException {

    private final int status;
    private final String message;


    public GithubClientException(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
