package pl.balukiewicz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Bartek on 06.03.2017.
 */

@Data
@AllArgsConstructor
public class RepositoryData {

    @JsonProperty("full_name")
    private String fullName;

    private String description;

    @JsonProperty("clone_url")
    private String cloneUrl;

    @JsonProperty("stargazers_count")
    private Integer stars;

    @JsonProperty("created_at")
    private String createdAt;

    public static RepositoryData getEmptyData() {
        return new RepositoryData("", "", "", 0, "");
    }

}
