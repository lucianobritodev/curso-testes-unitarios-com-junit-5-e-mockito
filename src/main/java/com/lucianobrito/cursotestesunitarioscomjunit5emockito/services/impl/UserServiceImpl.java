package com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.impl;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.User;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.dto.UserDto;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.repositories.UserRepository;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.UserService;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserDto> findByAll() {
        return repository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = repository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserDto.class);
    }


}
