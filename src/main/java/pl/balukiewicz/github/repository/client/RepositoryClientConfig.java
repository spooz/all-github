package pl.balukiewicz.github.repository.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.balukiewicz.github.repository.client.exception.GithubClientException;
import pl.balukiewicz.github.repository.client.exception.GithubServerException;

import static feign.FeignException.errorStatus;

@Configuration
@EnableFeignClients
class RepositoryClientConfig {

    @Bean
    RepositoryClientErrorDecoder errorDecoder() {
        return new RepositoryClientErrorDecoder();
    }

}

class RepositoryClientErrorDecoder implements ErrorDecoder {

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