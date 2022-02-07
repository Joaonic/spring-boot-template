package com.template.spring.core.exceptions.custom;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String messageKey, Object... messageArgs) {

        super(messageKey, ExceptionTypesEnum.UNAUTHORIZED_EXCEPTION, messageArgs);

    }

}
