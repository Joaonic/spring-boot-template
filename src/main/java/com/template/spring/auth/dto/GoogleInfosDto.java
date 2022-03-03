package com.template.spring.auth.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class GoogleInfosDto implements Serializable {

    private final GoogleProfileObj profileObj;
    private final String tokenId;
    private final String accessToken;
    private final String googleId;

    @JsonCreator
    public GoogleInfosDto(@JsonProperty("profileObj") GoogleProfileObj profileObj,
                          @JsonProperty("tokenId") String tokenId,
                          @JsonProperty("accessToken") String accessToken,
                          @JsonProperty("googleId") String googleId) {
        this.profileObj = profileObj;
        this.tokenId = tokenId;
        this.accessToken = accessToken;
        this.googleId = googleId;
    }
}
