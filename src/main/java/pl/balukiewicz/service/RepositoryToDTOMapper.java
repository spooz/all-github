package pl.balukiewicz.service;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import pl.balukiewicz.client.Repository;
import pl.balukiewicz.dto.RepositoryDTO;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Component
class RepositoryToDTOMapper {

    public RepositoryDTO map(Repository repository) {
        return map(repository, LocaleContextHolder.getLocale());
    }

    public RepositoryDTO map(Repository repository, Locale locale) {
        return new RepositoryDTO(repository.getFullName(),
                repository.getDescription(),
                repository.getCloneUrl(),
                repository.getStars(),
                repository.getCreatedAt().format(getDateTimeFormatter(locale)));
    }

    private DateTimeFormatter getDateTimeFormatter(Locale locale) {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(locale);
    }

}
