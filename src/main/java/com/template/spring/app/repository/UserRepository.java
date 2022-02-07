package com.template.spring.app.repository;

import com.template.spring.app.model.User;
import com.template.spring.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.email=?1 or u.username=?1 and u.deletedAt is null")
    Optional<User> findByLogin(String login);
}
