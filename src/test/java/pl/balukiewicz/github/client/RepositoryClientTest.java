package pl.balukiewicz.github.client;


import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.balukiewicz.github.repository.client.Repository;
import pl.balukiewicz.github.repository.client.RepositoryClient;
import pl.balukiewicz.github.repository.client.exception.RepositoryClientException;
import pl.balukiewicz.github.repository.client.exception.RepositoryServerException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RepositoryClientTest {

    @Autowired
    private RepositoryClient repositoryClient;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089));

    @Test
    public void shouldReturnHelloWorldRepository() {

        //given
        stubFor(get(urlEqualTo("/repos/octocat/Hello-World"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("octocat_hello_world.json")));

        //given
        Repository repository = repositoryClient.getRepository("octocat", "Hello-World");


        //then
        assertThat(repository.getFullName()).isEqualTo("octocat/Hello-World");
        assertThat(repository.getDescription()).isEqualTo("My first repository on GitHub!");
        assertThat(repository.getCloneUrl()).isEqualTo("https://github.com/octocat/Hello-World.git");
        assertThat(repository.getStars()).isEqualTo(1407);
        assertThat(repository.getCreatedAt()).isEqualTo("2011-01-26T19:01:12Z");
    }

    @Test
    public void shouldThrowRepositoryClientException() {
        //given
        stubFor(get(anyUrl())
                .willReturn(aResponse().withStatus(404)));

        assertThatThrownBy(() -> repositoryClient.getRepository("octocat", "Hello-World"))
                .isInstanceOf(RepositoryClientException.class);
    }

    @Test
    public void shouldThrowRepositoryServerException() {
        stubFor(get(anyUrl())
                .willReturn(aResponse().withStatus(500)));

        assertThatThrownBy(() -> repositoryClient.getRepository("octocat", "Hello-World"))
                .isInstanceOf(RepositoryServerException.class);

    }
}
