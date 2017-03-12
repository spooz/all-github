package pl.balukiewicz;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AllGithubApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}


	@Rule
	public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089));



	@Test
	public void shouldReturnHelloWorld() throws Exception {

		stubFor(get(urlEqualTo("/repos/octocat/Hello-World"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withBodyFile("octocat_hello_world.json")));

		mockMvc.perform(
				org.springframework.test.web.servlet.request.MockMvcRequestBuilders.
						get("/repositories/octocat/Hello-World"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.full_name").value( "octocat/Hello-World"))
				.andExpect(jsonPath("$.description").value("My first repository on GitHub!"))
				.andExpect(jsonPath("$.clone_url").value("https://github.com/octocat/Hello-World.git"))
				.andExpect(jsonPath("$.stargazers_count").value(1407))
				.andExpect(jsonPath("$.created_at").value("2011-01-26T19:01:12Z"));

	}

}
