package com.mgoldblat.auth.service;

import com.mgoldblat.auth.domain.Role;
import com.mgoldblat.auth.domain.User;
import com.mgoldblat.auth.repository.RoleRepository;
import com.mgoldblat.auth.repository.UserRepository;
import com.mgoldblat.auth.filter.AuthorizationRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User create(AuthorizationRequest authorizationRequest, Role.Name roleName) {
        User user = this.userRepository
                .findByUsername(authorizationRequest.getUsername());

        if (user != null) {
            throw new RuntimeException("Username already exists");
        }

        final Role role = this.roleRepository.findByName(roleName.name());
        final User newUser = User.builder()
                .username(authorizationRequest.getUsername())
                .password(this.bCryptPasswordEncoder.encode(authorizationRequest.getPassword()))
                .roles(Set.of(role))
                .build();

        return this.userRepository.save(newUser);
    }
}
