package pl.balukiewicz.github.repository.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;

@Data
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
