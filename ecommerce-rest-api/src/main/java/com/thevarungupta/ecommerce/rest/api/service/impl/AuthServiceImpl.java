package com.thevarungupta.ecommerce.rest.api.service.impl;

import com.thevarungupta.ecommerce.rest.api.entity.Role;
import com.thevarungupta.ecommerce.rest.api.entity.User;
import com.thevarungupta.ecommerce.rest.api.payload.LoginDto;
import com.thevarungupta.ecommerce.rest.api.payload.LoginResponse;
import com.thevarungupta.ecommerce.rest.api.payload.RegisterDto;
import com.thevarungupta.ecommerce.rest.api.payload.RegisterResponse;
import com.thevarungupta.ecommerce.rest.api.repository.RoleRepository;
import com.thevarungupta.ecommerce.rest.api.repository.UserRepository;
import com.thevarungupta.ecommerce.rest.api.security.JwtTokenProvider;
import com.thevarungupta.ecommerce.rest.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setMessage("login successful");
        response.setError(false);

        return response;
    }

    @Override
    public RegisterResponse register(RegisterDto registerDto) {
        // add check for username exists in database
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new RuntimeException("username is already exists!.");
        }
        // add check for email exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("email is already exists!.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role1 = new Role();
        role1.setName("ROLE_USER");
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");
        roleRepository.save(role2);

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        User savedUser =  userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setError(false);
        response.setMessage("register successful");
        response.setUser(savedUser);
        return response;
    }
}
