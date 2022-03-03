package com.template.spring.core.security.entities;

import com.template.spring.auth.enums.UserLevel;
import com.template.spring.auth.model.Role;
import com.template.spring.auth.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserPrincipal implements UserDetails {

    private final Long id;

    private final String email;

    private final String username;

    private final String password;

    private final List<UserLevel> level;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.authorities = null;
        this.email = user.getEmail();
        this.password = null;
        this.level = user.getRoles().stream().map(Role::getUserLevel).collect(Collectors.toList());
    }

    public UserPrincipal(Long id) {
        this.id = id;
        this.email = null;
        this.username = null;
        this.password = null;
        this.authorities = null;
        this.level = new ArrayList<>();
    }


    public UserPrincipal(Long id, String email, String username, String password, Collection<? extends GrantedAuthority> authorities, List<UserLevel> level) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.level = level;
    }

    public static UserPrincipal create(User user) {

        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getRoles().stream().map(Role::getUserLevel).collect(Collectors.toList())
        );
    }

    public static UserPrincipal create(User user, Collection<? extends GrantedAuthority> authorities) {

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getRoles().stream().map(Role::getUserLevel).collect(Collectors.toList())
        );
    }


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
