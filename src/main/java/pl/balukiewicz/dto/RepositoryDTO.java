package pl.balukiewicz.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class RepositoryDTO {

    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final Long stars;
    private final String createdAt;

}
