package com.example.demo.services;

import com.example.demo.DTO.JWTResponse;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.RefreshRequest;
import com.example.demo.DTO.RegisterRequest;

public interface AuthService {
     void register(RegisterRequest registerRequest);
     JWTResponse login(LoginRequest request);
     JWTResponse refreshToken(RefreshRequest refreshRequest);
}
