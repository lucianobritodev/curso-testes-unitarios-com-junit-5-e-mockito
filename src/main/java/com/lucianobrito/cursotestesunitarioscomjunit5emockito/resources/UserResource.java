package com.lucianobrito.cursotestesunitarioscomjunit5emockito.resources;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.dto.UserDto;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Validated
public class UserResource {

    private UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable @Positive(message = "{Positive.id}") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findByAll() {
        return ResponseEntity.ok(userService.findByAll());
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
        userDto = userService.create(userDto);
        final URI locationUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDto.getId())
                .toUri();
        return ResponseEntity.created(locationUri).body(userDto);
    }

}
