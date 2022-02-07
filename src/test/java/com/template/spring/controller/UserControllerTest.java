package com.template.spring.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserControllerTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentationContextProvider) {
        super.setUp(restDocumentationContextProvider);
    }

    @Test
    void findById() throws Exception {
        String uri = "/user/{id}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri, 1))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(document("find-by-id",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                /* SPRING REST BUG DONT LET DOCUMENT PATH PARAMETERS AS https://github.com/spring-projects/spring-framework/issues/25854 */
                .andReturn();
    }


}
