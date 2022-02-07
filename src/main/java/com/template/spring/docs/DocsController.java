package com.template.spring.docs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/public/docs")
@Controller
public class DocsController {

    @GetMapping("/api-guide")
    public String getWelcomePageAsModel() {
        return "api-guide";
    }
}
