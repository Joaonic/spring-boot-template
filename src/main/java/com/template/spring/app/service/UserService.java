package com.template.spring.app.service;


import com.template.spring.app.model.User;
import com.template.spring.app.repository.UserRepository;
import com.template.spring.core.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class UserService extends BaseService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

}
