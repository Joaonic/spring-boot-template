package com.template.spring.auth.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class GoogleProfileObj implements Serializable {

    private final String email;
    private final String familyName;
    private final String givenName;
    private final String imageUrl;
    private final String name;

    @JsonCreator
    public GoogleProfileObj(@JsonProperty("email") String email,
                            @JsonProperty("familyName") String familyName,
                            @JsonProperty("givenName") String givenName,
                            @JsonProperty("imageUrl") String imageUrl,
                            @JsonProperty("name") String name) {
        this.email = email;
        this.familyName = familyName;
        this.givenName = givenName;
        this.imageUrl = imageUrl;
        this.name = name;
    }
}
