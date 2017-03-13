package pl.balukiewicz.github;

import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import pl.balukiewicz.github.repository.RepositoryFacade;
import pl.balukiewicz.github.repository.client.exception.RepositoryClientException;
import pl.balukiewicz.github.repository.dto.RepositoryDTO;

import java.util.Locale;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class RepositoryEndpointTest {

    @InjectMocks
    private RepositoryEndpoint repositoryEndpoint;

    @Mock
    private RepositoryFacade repositoryFacade;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        mockMvc = standaloneSetup(repositoryEndpoint).build();
    }

    @Test
    public void shouldReturnRepository() throws Exception {
        //given
        RepositoryDTO repositoryDTO = new RepositoryDTO("fullName", "description",
                "cloneUrl", 1L, "createdAt");
        when(repositoryFacade.getRepository("test", "test", Locale.ENGLISH)).thenReturn(repositoryDTO);

        //when
        mockMvc.perform(get("/repositories/test/test").locale(Locale.ENGLISH))

                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(repositoryDTO.getFullName()))
                .andExpect(jsonPath("$.description").value(repositoryDTO.getDescription()))
                .andExpect(jsonPath("$.cloneUrl").value(repositoryDTO.getCloneUrl()))
                .andExpect(jsonPath("$.stars").value(repositoryDTO.getStars()))
                .andExpect(jsonPath("$.createdAt").value(repositoryDTO.getCreatedAt()));

        verify(repositoryFacade, atLeast(1))
                .getRepository("test", "test", Locale.ENGLISH);
    }

    @Test
    public void shouldReturnNotFoundWhenRepositoryClientExceptionWithNotFound() throws Exception {
        when(repositoryFacade.getRepository(anyString(), anyString(), any()))
                .thenThrow(new RepositoryClientException(404, "test"));

        mockMvc.perform(get("/repositories/test/test"))
                .andExpect(status().is(404));
    }


    @Test
    public void shouldReturnInternalErrorWhenRepositoryServerExceptionWithInternal() throws Exception {
        when(repositoryFacade.getRepository(anyString(), anyString(), any()))
                .thenThrow(new RepositoryClientException(500, "test"));

        mockMvc.perform(get("/repositories/test/test"))
                .andExpect(status().is(500));
    }

    @Test
    public void shouldReturnInternalErrorWhenRuntimeException() throws Exception {
        when(repositoryFacade.getRepository(anyString(), anyString(), any()))
                .thenThrow(new RuntimeException());

        mockMvc.perform(get("/repositories/test/test"))
                .andExpect(status().is(500));
    }

}
