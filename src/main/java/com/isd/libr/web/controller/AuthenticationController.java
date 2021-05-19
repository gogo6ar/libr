package com.isd.libr.web.controller;

import com.isd.libr.service.AuthenticationService;
import com.isd.libr.service.TokenService;
import com.isd.libr.web.entity.Person;
import com.isd.libr.web.dto.requests.LoginRequest;
import com.isd.libr.web.dto.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request) {
        String passwd = passwordEncoder.encode(request.getPasswd());
        Person user = authenticationService.create(request, passwd);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            Person person = (Person) authentication.getPrincipal();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Bearer", tokenService.createToken(person));
            return ResponseEntity.ok().headers(headers).body(person);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
