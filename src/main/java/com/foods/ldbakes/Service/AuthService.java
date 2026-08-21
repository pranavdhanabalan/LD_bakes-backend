package com.foods.ldbakes.Service;

import com.foods.ldbakes.DTO.AuthResponse;
import com.foods.ldbakes.DTO.LoginRequest;
import com.foods.ldbakes.Model.User;
import com.foods.ldbakes.Repository.UserRepository;
import com.foods.ldbakes.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest loginRequest) throws ResponseStatusException {
        User user=userRepository.findByUserEmail(loginRequest.email())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password"));

        if (!passwordEncoder.matches(loginRequest.password(),user.getUserPassword())){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid email or password"
            );
        }

        String accessToken=jwtService.generateAccessToken(user);
        String refreshToken=jwtService.generateRefreshToken(user);

        return new AuthResponse(accessToken,refreshToken,jwtService.getAccessTokenExpirySeconds());
    }
}
