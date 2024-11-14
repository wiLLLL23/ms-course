package com.devwill.hruser.controllers;

import com.devwill.hruser.entities.User;
import com.devwill.hruser.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserControllers {

    private UserRepository repository;

    public UserControllers(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable final Long id) {
        final var user = repository.findById(id).get();
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<User> findByEmail(@RequestParam final String email) {
        final var user = repository.findByEmail(email);
        return ResponseEntity.ok(user);
    }
}
