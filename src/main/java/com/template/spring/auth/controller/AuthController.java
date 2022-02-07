package com.template.spring.auth.controller;

import com.template.spring.auth.dto.LoginDto;
import com.template.spring.auth.service.AuthService;
import com.template.spring.core.exceptions.custom.UnauthorizedException;
import com.template.spring.core.web.BaseController;
import com.template.spring.core.web.json.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Json> authenticateUser(@Valid @RequestBody LoginDto loginRequest) throws UnauthorizedException {
        return renderJSON(authService.authenticateUser(loginRequest));
    }

}
