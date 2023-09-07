package com.lucianobrito.cursotestesunitarioscomjunit5emockito.resources;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(new User(id, "Maria", "maria@email.com","123"));
    }

}