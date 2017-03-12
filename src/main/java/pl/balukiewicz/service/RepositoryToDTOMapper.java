package pl.balukiewicz.service;

import org.springframework.stereotype.Component;
import pl.balukiewicz.client.Repository;
import pl.balukiewicz.dto.RepositoryDTO;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Component
class RepositoryToDTOMapper {

    public RepositoryDTO map(Repository repository, Locale locale) {
        return new RepositoryDTO(repository.getFullName(),
                repository.getDescription(),
                repository.getCloneUrl(),
                repository.getStars(),
                repository.getCreatedAt().format(getDateTimeFormatter(locale)));
    }

    private DateTimeFormatter getDateTimeFormatter(Locale locale) {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale);
    }

}
