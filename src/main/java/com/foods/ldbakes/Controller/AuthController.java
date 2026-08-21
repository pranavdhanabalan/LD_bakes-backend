package com.foods.ldbakes.Controller;

import com.foods.ldbakes.DTO.AuthResponse;
import com.foods.ldbakes.DTO.LoginRequest;
import com.foods.ldbakes.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) throws BadRequestException {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
