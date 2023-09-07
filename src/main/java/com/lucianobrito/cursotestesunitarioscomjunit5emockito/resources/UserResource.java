package com.lucianobrito.cursotestesunitarioscomjunit5emockito.resources;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.domain.User;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserResource {

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

}
