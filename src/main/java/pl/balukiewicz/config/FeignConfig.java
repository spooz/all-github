package pl.balukiewicz.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "pl.balukiewicz.client")
class FeignConfig {
}
