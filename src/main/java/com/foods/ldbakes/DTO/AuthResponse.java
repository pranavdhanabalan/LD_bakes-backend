package com.foods.ldbakes.DTO;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        long expiresIn
){ }
