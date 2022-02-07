package com.template.spring.auth.service;

import com.template.spring.app.model.User;
import com.template.spring.app.repository.UserRepository;
import com.template.spring.auth.dto.LoginDto;
import com.template.spring.core.config.Config;
import com.template.spring.core.exceptions.custom.UnauthorizedException;
import com.template.spring.core.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public String authenticateUser(LoginDto loginRequest) {
        User user = userRepository.findByLogin(loginRequest.getLogin()).orElseThrow(() -> new UnauthorizedException("user.password.login"));
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            loginRequest.getPassword() + Config.salt
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProvider.createToken(authentication);
        } catch (AuthenticationException ex) {
            throw new UnauthorizedException("user.password.login");
        }
    }


}
