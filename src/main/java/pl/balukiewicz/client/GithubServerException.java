package pl.balukiewicz.client;

import lombok.Getter;

@Getter
public class GithubServerException extends RuntimeException {

    private final int status;
    private final String message;


    public GithubServerException(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
