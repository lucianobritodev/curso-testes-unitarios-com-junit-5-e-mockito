package com.lucianobrito.cursotestesunitarioscomjunit5emockito.services;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto findById(Long id);
    List<UserDto> findByAll();
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);




}
