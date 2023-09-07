package com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.impl;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.User;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.repositories.UserRepository;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }


}
