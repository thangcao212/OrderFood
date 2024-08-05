package com.orderFood.ThangOrderFood.response;

import com.orderFood.ThangOrderFood.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;

    private String message;

    private USER_ROLE role;
}
