package pl.balukiewicz.dto;

import lombok.Getter;
import pl.balukiewicz.client.Repository;

import java.text.Normalizer;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * Created by spooz on 12.03.2017.
 */
@Getter
public class RepositoryDTO {

    private final String fullName;
    private final String description;
    private final String cloneUrl;
    private final Long stars;
    private final String createdAt;

    public RepositoryDTO(Repository repository, Locale locale) {
        this.fullName = repository.getFullName();
        this.description = repository.getDescription();
        this.cloneUrl = repository.getCloneUrl();
        this.stars = repository.getStars();

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(locale);
        this.createdAt = repository.getCreatedAt().format(formatter);
    }

}
