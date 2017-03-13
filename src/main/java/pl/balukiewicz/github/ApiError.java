package pl.balukiewicz.github;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class ApiError {
    private final String message;
}
