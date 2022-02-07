package com.template.spring.core.exceptions.custom;

import lombok.Getter;

@Getter
public enum ExceptionTypesEnum {

    BASE_EXCEPTION("Internal error!"),
    VALIDATION_EXCEPTION("Validation error!"),
    WARNING("WARNING"),
    UNAUTHORIZED_EXCEPTION("Authentication error");

    private final String descricao;

    ExceptionTypesEnum(String descricao) {
        this.descricao = descricao;
    }
}
