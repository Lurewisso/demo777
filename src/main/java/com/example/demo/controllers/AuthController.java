package com.example.demo.controllers;


import com.example.demo.DTO.RegisterRequest;
import com.example.demo.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

@Tag(name = "Auth API", description = "auth account")

public class AuthController {
    private final AuthService authService;


    @Operation(summary = "Register", description = "Register now", responses = {
            @ApiResponse(responseCode = "200",description = "Register successfully")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterRequest registerRequest){
        authService.register(registerRequest);
        return ResponseEntity.ok("Register was successfully");

    }

    @Operation(summary = "Login", description = "Login", responses = {
            @ApiResponse(responseCode = "200",description = "Register successfully")
    })
    @GetMapping
    public ResponseEntity<?> login(@RequestBody Authentication authentication){
        return ResponseEntity.ok("Login was successful" + authentication.getName());
    }
}
