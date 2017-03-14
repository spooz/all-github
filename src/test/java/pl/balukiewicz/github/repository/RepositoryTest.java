package pl.balukiewicz.github.repository;

import org.junit.Before;
import org.junit.Test;
import pl.balukiewicz.github.repository.client.Repository;
import pl.balukiewicz.github.repository.client.RepositoryClient;
import pl.balukiewicz.github.repository.dto.RepositoryDTO;

import java.time.ZonedDateTime;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryTest {

    private RepositoryFacade repositoryFacade;
    private RepositoryClient repositoryClient;

    @Before
    public void setup() {
        repositoryClient = mock(RepositoryClient.class);
        RepositoryConfig repositoryConfig = new RepositoryConfig();
        repositoryFacade = repositoryConfig.repositoryFacade(repositoryClient);
    }

    @Test
    public void shouldReturnRepositoryDTOWithGermanLocale() {

        Locale locale = Locale.GERMAN;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2011-01-26T19:01:12Z");
        String createdAt = "26.01.2011 19:01:12";
        Repository repository = new Repository("fullName", "description", "cloneUrl",
                1L, zonedDateTime);

        testFacadeWithParameters(locale, createdAt, repository);
    }

    @Test
    public void shouldReturnRepositoryDTOWithEnglishLocale() {

        Locale locale = Locale.ENGLISH;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2011-01-26T19:01:12Z");
        String createdAt = "Jan 26, 2011 7:01:12 PM";
        Repository repository = new Repository("fullName", "description", "cloneUrl",
                1L, zonedDateTime);

        testFacadeWithParameters(locale, createdAt, repository);
    }

    @Test
    public void shouldReturnEmptyValuesWithEmptyApiResponse() {
        Repository repository = new Repository("", "", "", null, null);

        testFacadeWithParameters(Locale.ENGLISH, "", repository);
    }



    private void testFacadeWithParameters(Locale locale, String createdAt,  Repository repository) {
        //given
        when(repositoryClient.getRepository("test", "test")).thenReturn(repository);


        //when
        RepositoryDTO repositoryDTO = repositoryFacade.getRepository("test", "test", locale);


        //then
        assertThat(repositoryDTO.getFullName()).isEqualTo(repository.getFullName());
        assertThat(repositoryDTO.getDescription()).isEqualTo(repository.getDescription());
        assertThat(repositoryDTO.getCloneUrl()).isEqualTo(repository.getCloneUrl());
        assertThat(repositoryDTO.getStars()).isEqualTo(repository.getStars());
        assertThat(repositoryDTO.getCreatedAt()).isEqualTo(createdAt);
    }




}
