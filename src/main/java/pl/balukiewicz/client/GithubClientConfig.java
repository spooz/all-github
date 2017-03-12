package pl.balukiewicz.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

import static feign.FeignException.errorStatus;

public class GithubClientConfig {

    @Bean
    public GithubErrorDecoder errorDecoder() {
        return new GithubErrorDecoder();
    }

}

class GithubErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            return new GithubClientException(
                    response.status(),
                    response.reason()
            );
        }

        if (response.status() >= 500 && response.status() <= 599) {
            return new GithubServerException(
                    response.status(),
                    response.reason()
            );
        }
        return errorStatus(methodKey, response);
    }

}