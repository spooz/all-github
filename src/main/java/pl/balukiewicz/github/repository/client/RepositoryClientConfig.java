package pl.balukiewicz.github.repository.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.balukiewicz.github.repository.client.exception.RepositoryClientException;
import pl.balukiewicz.github.repository.client.exception.RepositoryServerException;

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
            return new RepositoryClientException(
                    response.status(),
                    response.reason()
            );
        }

        if (response.status() >= 500 && response.status() <= 599) {
            return new RepositoryServerException(
                    response.status(),
                    response.reason()
            );
        }
        return errorStatus(methodKey, response);
    }

}