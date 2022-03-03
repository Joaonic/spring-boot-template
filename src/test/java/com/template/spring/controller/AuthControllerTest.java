package com.template.spring.controller;

import com.template.spring.auth.dto.LoginDto;
import com.template.spring.auth.enums.AuthType;
import com.template.spring.core.config.Config;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends AbstractTest {

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider, @Autowired PasswordEncoder passwordEncoder) {
        super.setUp(restDocumentationContextProvider);
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    void authenticate() throws Exception {
        String uri = "/api/auth/login";
        LoginDto loginDto = new LoginDto("test", "password", null, null, AuthType.BASIC);
        String inputJson = super.mapToJson(loginDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andExpect(status().is(200))
                .andDo(document("authenticate",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("login")
                                        .description("Username or email of user requesting authentication"),
                                fieldWithPath("password")
                                        .description("Password of user requesting authentication"),
                                fieldWithPath("facebookInfos")
                                        .description("Infos from facebook when authenticating by them"),
                                fieldWithPath("googleInfos")
                                        .description("Infos from google when authenticating by them"),
                                fieldWithPath("type")
                                        .description("Authentication type")
                        )))
                .andReturn();
    }

    @Test
    void createSeanha() {
        this.passwordEncoder.encode("password" + Config.salt);
    }


}
