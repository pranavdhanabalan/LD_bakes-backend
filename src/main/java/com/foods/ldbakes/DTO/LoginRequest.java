package com.foods.ldbakes.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message="Email is Required")
        @Email(message="Email must be a valid email id.")
        String email,
        @NotBlank(message="password is required")
        String password
) { }
