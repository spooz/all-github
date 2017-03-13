package pl.balukiewicz.github.repository;

import pl.balukiewicz.github.repository.client.Repository;
import pl.balukiewicz.github.repository.dto.RepositoryDTO;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

class RepositoryToDTOMapper {

    RepositoryDTO map(Repository repository, Locale locale) {
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
