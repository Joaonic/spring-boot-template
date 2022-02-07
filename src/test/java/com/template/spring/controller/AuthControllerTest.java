package com.template.spring.controller;

import com.template.spring.auth.dto.LoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider) {
        super.setUp(restDocumentationContextProvider);
    }

    @Test
    void authenticate() throws Exception {
        String uri = "/auth/login";
        LoginDto loginDto = new LoginDto("test", "password");
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
                                        .description("Password of user requesting authentication")
                        )))
                .andReturn();
    }

}
