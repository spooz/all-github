package pl.balukiewicz.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

    private final String message;

}
