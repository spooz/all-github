package pl.balukiewicz;

import com.github.tomakehurst.wiremock.http.Fault;
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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

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
	public void shouldReturnHelloWorldWithPLLocale() throws Exception {

		//given
		Locale locale = Locale.forLanguageTag("pl");
		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2011-01-26T19:01:12Z");
		String createdAt = zonedDateTime.format(DateTimeFormatter
				.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale));

		stubFor(get(urlEqualTo("/repos/octocat/Hello-World"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withBodyFile("octocat_hello_world.json")));

		//when
		mockMvc.perform(
				org.springframework.test.web.servlet.request.MockMvcRequestBuilders.
						get("/repositories/octocat/Hello-World")
						.locale(locale)
						)
				//then
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.fullName").value( "octocat/Hello-World"))
				.andExpect(jsonPath("$.description").value("My first repository on GitHub!"))
				.andExpect(jsonPath("$.cloneUrl").value("https://github.com/octocat/Hello-World.git"))
				.andExpect(jsonPath("$.stars").value(1407))
				.andExpect(jsonPath("$.createdAt").value(createdAt));

	}

	@Test
	public void shouldReturnHelloWorldWithENLocale() throws Exception {

		//given
		Locale locale = Locale.forLanguageTag("en");
		ZonedDateTime zonedDateTime = ZonedDateTime.parse("2011-01-26T19:01:12Z");
		String createdAt = zonedDateTime.format(DateTimeFormatter
				.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale));

		stubFor(get(urlEqualTo("/repos/octocat/Hello-World"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withBodyFile("octocat_hello_world.json")));

		//when
		mockMvc.perform(
				org.springframework.test.web.servlet.request.MockMvcRequestBuilders.
						get("/repositories/octocat/Hello-World")
						.locale(locale)
		)
				//then
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.fullName").value( "octocat/Hello-World"))
				.andExpect(jsonPath("$.description").value("My first repository on GitHub!"))
				.andExpect(jsonPath("$.cloneUrl").value("https://github.com/octocat/Hello-World.git"))
				.andExpect(jsonPath("$.stars").value(1407))
				.andExpect(jsonPath("$.createdAt").value(createdAt));

	}

	@Test
	public void shouldReturn404WhenNoRepository() throws Exception {

		//given
		// mocking github's 404 response
		stubFor(get(anyUrl())
				.willReturn(aResponse().withStatus(404)));

		//when
		mockMvc.perform(
				org.springframework.test.web.servlet.request.MockMvcRequestBuilders.
						get("/repositories/octocat/Hello-World"))
				//then
				.andExpect(status().is(404));

	}

	@Test
	public void shouldReturn500WhenApiInternalError() throws Exception {

		//given
		// mocking github's 500 response
		stubFor(get(anyUrl())
				.willReturn(aResponse().withStatus(500)));

		//when
		mockMvc.perform(
				org.springframework.test.web.servlet.request.MockMvcRequestBuilders.
						get("/repositories/octocat/Hello-World"))
				//then
				.andExpect(status().is(500));

	}

	@Test
	public void shouldReturn500WhenApiTimeout() throws Exception {

		//given
		// mocking github's 500 response
		stubFor(get(anyUrl())
				.willReturn(aResponse().withStatus(200).withFixedDelay(5000)));

		//when
		mockMvc.perform(
				org.springframework.test.web.servlet.request.MockMvcRequestBuilders.
						get("/repositories/octocat/Hello-World"))
				//then
				.andExpect(status().is(500));

	}

}
