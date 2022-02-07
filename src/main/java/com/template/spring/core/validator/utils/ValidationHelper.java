package com.template.spring.core.validator.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Component
public class ValidationHelper {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private static net.sf.oval.Validator ovalValidator;

    public static Boolean isSubClassValid(Object o) {
        Set<ConstraintViolation<Object>> errors = validator.validate(o);
        List<net.sf.oval.ConstraintViolation> ovalErrors = ovalValidator.validate(o);

        return (errors == null || errors.isEmpty()) && (ovalErrors == null || ovalErrors.isEmpty());
    }

    @Autowired
    public void setOvalValidator(net.sf.oval.Validator ovalValidator) {

        ValidationHelper.ovalValidator = ovalValidator;
    }
}
