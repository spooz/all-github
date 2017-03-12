package pl.balukiewicz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
class LocaleConfig {

    @Bean
    AcceptHeaderLocaleResolver acceptHeaderLocaleResolver() {
        return new AcceptHeaderLocaleResolver();
    }

}
