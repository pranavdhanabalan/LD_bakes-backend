package com.foods.ldbakes.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private JwtAuthenticationFilter jwtAuthenticationFilter;
}
