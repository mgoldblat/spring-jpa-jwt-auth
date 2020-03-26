package com.mgoldblat.auth.controller;

import com.mgoldblat.auth.repository.UserRepository;
import com.mgoldblat.auth.filter.AuthorizationRequest;
import com.mgoldblat.auth.domain.Role;
import com.mgoldblat.auth.domain.User;
import com.mgoldblat.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomersController {

    private final UserRepository userRepository;
    private final UserService userService;

    public CustomersController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<User> getById(@PathVariable long id) {
        final User user = this.userRepository.findById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAll() {
        final List<User> users = this.userRepository.findAll();

        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody AuthorizationRequest authorizationRequest) {
final User user = this.userService.create(authorizationRequest, Role.Name.CUSTOMER);
return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
