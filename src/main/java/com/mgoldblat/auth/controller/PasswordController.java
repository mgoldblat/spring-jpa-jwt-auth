package com.mgoldblat.auth.controller;

import com.mgoldblat.auth.domain.User;
import com.mgoldblat.auth.repository.RoleRepository;
import com.mgoldblat.auth.filter.AuthorizationRequest;
import com.mgoldblat.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
@CrossOrigin
@RequiredArgsConstructor
public class PasswordController {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserService userService;
	private final RoleRepository roleRepository;

	@GetMapping
	public ResponseEntity<User> getPassword(@RequestBody AuthorizationRequest userRequest) {
		final User user = User.builder()
				.password(bCryptPasswordEncoder.encode(userRequest.getPassword()))
				.build();

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
