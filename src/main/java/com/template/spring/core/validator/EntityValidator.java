package com.template.spring.core.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EntityValidator<T> {

    private final List<GenericValidationResult> validations = new ArrayList<>();

    public List<GenericValidationResult> validate(T entity) {
        return Collections.emptyList();
    }

    public List<GenericValidationResult> isValidate() {

        return validations.stream().filter(c -> !c.isValid()).collect(Collectors.toList());
    }

    public void addValidate(GenericValidationResult validationResult) {

        validations.add(validationResult);
    }
}
