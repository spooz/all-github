package pl.balukiewicz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AllGithubApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllGithubApplication.class, args);
	}
}
