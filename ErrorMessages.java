package ru.arkhipov.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {

    EMPTY(""),

    VALIDATION_ERROR("Ошибка валидации"),
    UNSUPPORTED("Произошла непредвиденная ошибка"),
    
    UNKNOWN ("Неизвестная ошибка");

    private final String description;


    ErrorMessages(String description) {
        this.description = description;
    }

    @JsonValue
    public String getName() {
        return description;
    }
}
