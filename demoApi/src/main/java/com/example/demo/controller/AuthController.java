package com.example.demo.controller;

import com.example.demo.model.LibraryUser;
import com.example.demo.repository.LibraryUserRepository;
import com.example.demo.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private LibraryUserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, LibraryUserRepository userRepo, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    private ResponseEntity<String> authenticateUser(@RequestBody LibraryUser libraryUser) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                libraryUser.getUsername(),
                libraryUser.getPassword()
        );

        Authentication auth = authenticationManager.authenticate(token);

        var json = "{\"\":\"" + tokenService.generateToken(auth) + "\"}";

        return new ResponseEntity<>(tokenService.generateToken(auth), HttpStatus.OK);
    }

    @PostMapping("/signup")
    private ResponseEntity<String> signuoUser(@RequestBody LibraryUser user) {
        if (userRepo.findLibraryUserByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>("user already exists", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
