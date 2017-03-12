package pl.balukiewicz.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Repository {

    @JsonProperty("full_name")
    private final String fullName;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("clone_url")
    private final String cloneUrl;

    @JsonProperty("stargazers_count")
    private final Long stars;

    @JsonProperty("created_at")
    private final ZonedDateTime createdAt;

}
