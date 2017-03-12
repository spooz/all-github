package pl.balukiewicz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class RepositoryDTO {

    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final Long stars;
    private final String createdAt;

}
