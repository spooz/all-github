package pl.balukiewicz.github.repository;

import pl.balukiewicz.github.repository.client.Repository;
import pl.balukiewicz.github.repository.dto.RepositoryDTO;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

class RepositoryToDTOMapper {

    RepositoryDTO map(Repository repository, Locale locale) {
        return new RepositoryDTO(repository.getFullName(),
                repository.getDescription(),
                repository.getCloneUrl(),
                repository.getStars(),
                formatDate(repository.getCreatedAt(), locale));
    }

    private DateTimeFormatter getDateTimeFormatter(Locale locale) {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale);
    }

    private String formatDate(ZonedDateTime zonedDateTime, Locale locale) {
        return zonedDateTime != null ? zonedDateTime.format(getDateTimeFormatter(locale)) : "";
    }

}
