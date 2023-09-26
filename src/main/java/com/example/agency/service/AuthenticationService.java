package com.example.agency.service;

import com.example.agency.config.JwtService;
import com.example.agency.dao.entity.User;
import com.example.agency.dao.repository.UserRepository;
import com.example.agency.enums.Role;
import com.example.agency.request.AuthenticationRequest;
import com.example.agency.request.RegisterRequest;
import com.example.agency.response.AuthenticationResponse;
import com.example.agency.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public RegisterResponse register(RegisterRequest request) {
        var exist = userRepo.getUserByEmail(request.getEmail()).isPresent();
        if (exist) {
            throw new RuntimeException("Username already exist");
        }
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var userEntity = userRepo.save(user);
        return RegisterResponse.buildRegisterDto(userEntity);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepo.getUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("No such user!"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
