package com.thevarungupta.ecommerce.rest.api.service;

import com.thevarungupta.ecommerce.rest.api.payload.LoginDto;
import com.thevarungupta.ecommerce.rest.api.payload.LoginResponse;
import com.thevarungupta.ecommerce.rest.api.payload.RegisterDto;
import com.thevarungupta.ecommerce.rest.api.payload.RegisterResponse;

public interface AuthService {
    LoginResponse login(LoginDto loginDto);
    RegisterResponse register(RegisterDto registerDto);
}
