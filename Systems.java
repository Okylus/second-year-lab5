package ru.arkhipov.MySecondTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Systems {
    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    WMS("Warehouse Management System"),
    SERVICE_1("Service 1");

    private final String description;

    Systems(String description) {
        this.description = description;
    }

    @JsonCreator
    public static Systems fromValue(String value) {
        if (value == null) {
            return null;
        }

        String normalizedValue = value.trim();

        for (Systems system : values()) {
            if (system.name().equalsIgnoreCase(normalizedValue)
                    || system.description.equalsIgnoreCase(normalizedValue)) {
                return system;
            }
        }

        throw new IllegalArgumentException("Unsupported system name: " + value);
    }

    @JsonValue
    public String getCode() {
        return name();
    }

    @Override
    public String toString() {
        return name();
    }

    public String getDescription() {
        return description;
    }
}
