package com.template.spring.core.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Messages {

    private static MessageSourceAccessor accessor;
    @Autowired
    private MessageSource messageSource;

    public static String get(String code, Object... args) {

        return accessor != null ? accessor.getMessage(code, args) : code;

    }

    @PostConstruct
    private synchronized void init() {

        accessor = new MessageSourceAccessor(messageSource);

    }
}
