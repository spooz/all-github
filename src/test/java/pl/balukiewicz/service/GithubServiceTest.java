package pl.balukiewicz.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.balukiewicz.client.GithubClient;
import pl.balukiewicz.client.Repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GithubServiceTest {

    @InjectMocks
    private GithubService githubService;

    @Mock
    private GithubClient githubClient;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldReturnRepository() {

        //given
        String owner = "test";
        String repoName = "test";
        Repository repository = mock(Repository.class);
        when(githubClient.getRepository(owner, repoName)).thenReturn(repository);

        //when
        Repository answer = githubService.getRepositoryData(owner, repoName);

        //then
        verify(githubClient, atMost(1)).getRepository(owner, repoName);
        assertEquals(answer, repository);

    }



}
