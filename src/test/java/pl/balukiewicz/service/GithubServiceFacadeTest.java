package pl.balukiewicz.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class GithubServiceFacadeTest {

    @InjectMocks
    private GithubServiceFacade githubServiceFacade;

    @Mock
    private GithubService githubService;

    @Before
    public void setup() {
        initMocks(this);
    }


}
