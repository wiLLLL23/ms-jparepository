package com.devwill.jparespository.controllers;

import com.devwill.jparespository.entities.User;
import com.devwill.jparespository.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        final var users = repository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<User>> findAll(final Pageable pageable) {
        final var users = repository.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/search-salary")
    public ResponseEntity<Page<User>> searchBySalary(
            @RequestParam(defaultValue = "0") final Double minSalary,
            @RequestParam(defaultValue = "1000000000000") final Double maxSalary,
            final Pageable pageable
    ) {
        final var users = repository.searchBySalary(minSalary, maxSalary, pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/search-name")
    public ResponseEntity<Page<User>> searchByName(
            @RequestParam(defaultValue = "") final String name,
            final Pageable pageable
    ) {
        final var users = repository.searchByName(name, pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/new-search-salary")
    public ResponseEntity<Page<User>> findBySalary(
            @RequestParam(defaultValue = "0") final Double minSalary,
            @RequestParam(defaultValue = "1000000000000") final Double maxSalary,
            final Pageable pageable
    ) {
        final var users = repository.findBySalaryBetween(minSalary, maxSalary, pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/new-search-name")
    public ResponseEntity<Page<User>> findByName(
            @RequestParam(defaultValue = "") final String name,
            final Pageable pageable
    ) {
        final var users = repository.findByNameContainingIgnoreCase(name, pageable);
        return ResponseEntity.ok(users);
    }
}
