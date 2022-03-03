package com.template.spring.auth.service;


import com.template.spring.auth.dto.GoogleProfileObj;
import com.template.spring.auth.model.User;
import com.template.spring.auth.repository.RoleRepository;
import com.template.spring.auth.repository.UserRepository;
import com.template.spring.core.exceptions.custom.UnauthorizedException;
import com.template.spring.core.service.BaseService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService extends BaseService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final Random rand = new SecureRandom();

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new UnauthorizedException("user.password.login"));
    }

    @Transactional
    public User save(GoogleProfileObj googleProfileObj) {
        return userRepository.save(
                User.builder()
                        .username(generateUniqueUsername(googleProfileObj.getName()))
                        .email(googleProfileObj.getEmail())
                        .image(googleProfileObj.getImageUrl())
                        .roles(Set.of(roleRepository.findByName("ROLE_USER")))
                        .build());

    }

    private String generateUniqueUsername(String name) {
        StringBuilder username = new StringBuilder(StringUtils.stripAccents(name).toLowerCase(Locale.ROOT).trim().replace(" ", ""));

        while (Boolean.TRUE.equals(userRepository.existsByUsername(username.toString()))) {
            username.append(rand.nextInt(10));
        }

        return username.toString();

    }

}
