package pl.balukiewicz.github.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.balukiewicz.github.repository.client.RepositoryClient;

//using package scope, to hide excessive visibility
@Configuration
class RepositoryConfig {

    @Bean
    RepositoryFacade repositoryFacade(RepositoryClient repositoryClient) {
        RepositoryService repositoryService = new RepositoryService(repositoryClient);
        RepositoryToDTOMapper repositoryToDTOMapper = new RepositoryToDTOMapper();
        return new RepositoryFacade(repositoryService, repositoryToDTOMapper);
    }


}
