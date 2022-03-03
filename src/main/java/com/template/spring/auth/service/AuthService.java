package com.template.spring.auth.service;

import com.template.spring.auth.dto.GoogleProfileObj;
import com.template.spring.auth.dto.LoginDto;
import com.template.spring.auth.model.User;
import com.template.spring.auth.repository.UserRepository;
import com.template.spring.auth.validations.GoogleAuthValidation;
import com.template.spring.core.config.Config;
import com.template.spring.core.exceptions.custom.UnauthorizedException;
import com.template.spring.core.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public String login(LoginDto loginRequest) {
        switch (loginRequest.getType()) {
            case BASIC:
                return authenticateUser(loginRequest);
            case GOOGLE:
                return googleAuth(loginRequest);
            case FACEBOOK:
                return null;
        }
        return null;
    }

    public String authenticateUser(LoginDto loginRequest) {
        User user = userService.findByLogin(loginRequest.getLogin());
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

    public String googleAuth(LoginDto loginDto) {
        GoogleAuthValidation.verify(loginDto.getGoogleInfos().getTokenId());

        GoogleProfileObj googleProfileObj = loginDto.getGoogleInfos().getProfileObj();

        Optional<User> user = userRepository.findByLogin(googleProfileObj.getEmail()).or(() -> Optional.ofNullable(userService.save(googleProfileObj)));
        if (user.isPresent()) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.get(), null,
                    AuthorityUtils.createAuthorityList("ROLE_USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProvider.createToken(authentication);
        }
        return null;
    }


}
