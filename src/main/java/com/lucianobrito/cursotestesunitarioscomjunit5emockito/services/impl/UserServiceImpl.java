package com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.impl;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.User;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.dto.UserDto;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.repositories.UserRepository;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.UserService;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.exceptions.ResourceAlreadyException;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {

    private UserRepository repository;

    @Override
    public UserDto findById(Long id) {
        return this.entityToDto(
                repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                getMessage("ResourceNotFoundMessage"))), UserDto.class);
    }

    @Override
    public List<UserDto> findByAll() {
        return repository.findAll()
                .stream()
                .map(user -> this.entityToDto(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserDto userDto) {
        alreadyEmail(userDto);
        User user = repository.save(this.dtoToEntity(userDto, User.class));
        return this.entityToDto(user, UserDto.class);
    }

    @Override
    public UserDto update(UserDto userDto) {
        return this.entityToDto(this.repository.save(this.dtoToEntity(userDto, User.class)), UserDto.class);
    }

    private void alreadyEmail(UserDto userDto) {
        if (repository.findByEmail(userDto.getEmail()).isPresent())
            throw new ResourceAlreadyException(
                    getMessage("ResourceAlreadyException", userDto.getEmail()));
    }

}
