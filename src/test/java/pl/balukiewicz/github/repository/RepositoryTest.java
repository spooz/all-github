package pl.balukiewicz.github.repository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.balukiewicz.github.repository.client.Repository;
import pl.balukiewicz.github.repository.client.RepositoryClient;
import pl.balukiewicz.github.repository.dto.RepositoryDTO;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RepositoryTest {

    private RepositoryFacade repositoryFacade;

    @Mock
    private RepositoryClient repositoryClient;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setup() {
        RepositoryConfig repositoryConfig = new RepositoryConfig();
        repositoryFacade = repositoryConfig.repositoryFacade(repositoryClient);
    }

    @Test
    public void shouldReturnRepositoryDTOWithEnglishLocale() {

        Locale locale = Locale.ENGLISH;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2011-01-26T19:01:12Z");
        String createdAt = zonedDateTime.format(DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale));
        Repository repository = new Repository("fullName", "description", "cloneUrl",
                1L, zonedDateTime);

        when(repositoryClient.getRepository("test", "test")).thenReturn(repository);


        RepositoryDTO repositoryDTO = repositoryFacade.getRepository("test", "test", locale);


        assertThat(repositoryDTO.getFullName(), is(repository.getFullName()));
        assertThat(repositoryDTO.getDescription(), is(repository.getDescription()));
        assertThat(repositoryDTO.getCloneUrl(), is(repository.getCloneUrl()));
        assertThat(repositoryDTO.getStars(), is(repository.getStars()));
        assertThat(repositoryDTO.getCreatedAt(), is(createdAt));
    }

}
