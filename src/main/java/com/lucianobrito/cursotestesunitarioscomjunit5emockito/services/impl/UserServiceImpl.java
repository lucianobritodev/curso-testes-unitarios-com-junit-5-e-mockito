package com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.impl;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.dto.UserDto;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.repositories.UserRepository;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.UserService;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private ModelMapper modelMapper;

    @Override
    public UserDto findById(Long id) {
        return modelMapper.map(
                repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Recurso não Encontrado!")), UserDto.class);
    }


}
