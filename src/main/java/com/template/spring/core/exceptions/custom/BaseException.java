package com.template.spring.core.exceptions.custom;

import com.template.spring.core.helpers.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BaseException extends RuntimeException {

    private String messageKey;
    private String messageTitle;
    private Object[] messageArgs;

    public BaseException() {

    }

    public BaseException(String messageKey, ExceptionTypesEnum exceptionType, Object... args) {

        super(Messages.get(messageKey, args));
        this.messageKey = messageKey;
        this.messageTitle = args.length > 0 && !String.valueOf(args[0]).isEmpty() ? Messages.get((String) args[0]) : exceptionType.getDescricao();

    }

    public String getUserMessage() {

        return Messages.get(this.messageKey, messageArgs);
    }

    public String getMessageTitle() {

        return this.messageTitle;
    }

    public BaseException userMessage(String messageKey, Object... messageArgs) {

        this.messageArgs = messageArgs;
        this.messageKey = messageKey;

        return this;

    }

}
