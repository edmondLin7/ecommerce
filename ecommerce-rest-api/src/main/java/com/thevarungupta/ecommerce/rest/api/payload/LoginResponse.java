package com.thevarungupta.ecommerce.rest.api.payload;

import com.thevarungupta.ecommerce.rest.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private boolean error;
    private String message;
    private String token;
}
