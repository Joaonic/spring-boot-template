package com.template.spring.core.exceptions.custom;

import com.template.spring.core.validator.utils.ValidationField;

import java.util.List;

public class ValidationException extends BaseException {

    private List<ValidationField> fieldErrors;

    public ValidationException() {

        super();

    }

    public ValidationException(String messageKey, Object... messageArgs) {

        super(messageKey, ExceptionTypesEnum.VALIDATION_EXCEPTION, messageArgs);

    }

    public List<ValidationField> getFieldErrors() {

        return this.fieldErrors;

    }

    public ValidationException setFieldErrors(List<ValidationField> fieldErrors) {

        this.fieldErrors = fieldErrors;

        return this;

    }

}
