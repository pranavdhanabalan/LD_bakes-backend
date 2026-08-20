package com.foods.ldbakes.Service;

import com.foods.ldbakes.DTO.AuthResponse;
import com.foods.ldbakes.DTO.LoginRequest;
import com.foods.ldbakes.Model.User;
import com.foods.ldbakes.Repository.UserRepository;
import com.foods.ldbakes.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest loginRequest){
        User user=userRepository.findByEmail(loginRequest.email())
        .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequest.password(),user.getUserPassword())){
            throw new BadRequestException("Invalid email or password");
        }

        String accessToken=jwtService.generateAccessToken(user);
        String refreshToken=jwtService.generateRefreshToken(user);

        return AuthResponse(accessToken,refreshToken,jwtService.getAccessTokenExpirySeconds());
    }
}
