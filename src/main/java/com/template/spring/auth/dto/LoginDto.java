package com.template.spring.auth.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.template.spring.auth.enums.AuthType;
import lombok.Getter;

@Getter
public class LoginDto {

    private final String login;
    private final String password;
    private final FacebookInfosDto facebookInfos;
    private final GoogleInfosDto googleInfos;
    private final AuthType type;

    @JsonCreator
    public LoginDto(@JsonProperty("login") String login, @JsonProperty("password") String password,
                    @JsonProperty("facebookInfos") FacebookInfosDto facebookInfos, @JsonProperty("googleInfos") GoogleInfosDto googleInfos,
                    @JsonProperty("type") AuthType type
    ) {
        this.login = login;
        this.password = password;
        this.facebookInfos = facebookInfos;
        this.googleInfos = googleInfos;
        this.type = type;
    }
}
