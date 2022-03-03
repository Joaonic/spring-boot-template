package com.template.spring.auth.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserLevel {
    ROLE_USER("ROLE_USER", "Usuário"),
    ROLE_ADMIN("ROLE_ADMIN", "Administrador");

    private final String code;
    private final String name;
}
