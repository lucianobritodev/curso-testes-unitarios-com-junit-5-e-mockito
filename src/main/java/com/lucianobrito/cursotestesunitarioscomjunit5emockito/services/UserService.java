package com.lucianobrito.cursotestesunitarioscomjunit5emockito.services;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.dto.UserDto;

public interface UserService {

    UserDto findById(Long id);

}
