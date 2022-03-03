package com.template.spring.auth.controller;

import com.template.spring.auth.mapper.UserMapper;
import com.template.spring.auth.service.UserService;
import com.template.spring.core.web.BaseController;
import com.template.spring.core.web.json.Json;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private final UserService userService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Json> findById(@PathVariable Long id) {
        return renderJSON(UserMapper.INSTANCE.userToUserDto(userService.findById(id)));
    }

}
