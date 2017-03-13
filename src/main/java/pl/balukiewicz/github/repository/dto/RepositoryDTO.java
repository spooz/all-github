package pl.balukiewicz.github.repository.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Data
public class RepositoryDTO {

    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final Long stars;
    private final String createdAt;

}
