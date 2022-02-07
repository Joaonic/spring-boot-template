package com.template.spring.core.validator;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class GenericValidationResult {

    private boolean valid;

    private String message;

    private GenericValidationResult(boolean valid, String message) {

        this.valid = valid;
        this.message = message;
    }

    public static GenericValidationResult ok() {

        return new GenericValidationResult(true, "");
    }

    public static GenericValidationResult error(String message) {

        return new GenericValidationResult(false, message);
    }

    public Optional<String> errorMessage() {

        return this.valid ? Optional.empty() : Optional.of(message);
    }
}
