package com.template.spring.core.service;

import com.template.spring.core.exceptions.custom.ValidatorException;
import com.template.spring.core.validator.GenericValidationResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseService {

    protected void throwValidatorException(String message) {
        throw new ValidatorException("error.validation", List.of(GenericValidationResult.error(message)));
    }
}
