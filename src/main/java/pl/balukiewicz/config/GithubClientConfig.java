package pl.balukiewicz.config;

import org.springframework.context.annotation.Bean;

/**
 * Created by spooz on 12.03.2017.
 */
public class GithubClientConfig {

    @Bean
    public GithubErrorDecoder errorDecoder() {
        return new GithubErrorDecoder();
    }

}
