package com.template.spring.core.validator;

import lombok.Getter;

import java.util.function.Predicate;

@Getter
public class GenericValidation<T> implements Validation<T> {

    private final Predicate<T> predicate;
    private String errorMessage;

    private GenericValidation(Predicate<T> predicate) {

        this.predicate = predicate;

    }

    private GenericValidation(Predicate<T> predicate, String errorMessage) {

        this(predicate);
        this.errorMessage = errorMessage;

    }

    public static <T> GenericValidation<T> from(Predicate<T> predicate) {

        return new GenericValidation<>(predicate);

    }

    public static <T> GenericValidation<T> from(Predicate<T> predicate, String errorMessage) {

        return new GenericValidation<>(predicate, errorMessage);

    }

    @Override
    public GenericValidationResult test(T param) {
        return predicate.test(param) ? GenericValidationResult.ok() : GenericValidationResult.error(this.getErrorMessage());
    }
}
