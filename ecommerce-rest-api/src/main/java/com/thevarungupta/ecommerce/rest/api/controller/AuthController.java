package com.thevarungupta.ecommerce.rest.api.controller;

import com.thevarungupta.ecommerce.rest.api.payload.LoginDto;
import com.thevarungupta.ecommerce.rest.api.payload.LoginResponse;
import com.thevarungupta.ecommerce.rest.api.payload.RegisterDto;
import com.thevarungupta.ecommerce.rest.api.payload.RegisterResponse;
import com.thevarungupta.ecommerce.rest.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) {
        var response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = {"/register", "signup"})
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterDto registerDto) {
        var response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
