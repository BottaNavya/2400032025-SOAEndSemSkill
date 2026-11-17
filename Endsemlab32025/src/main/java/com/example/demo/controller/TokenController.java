package com.example.demo.controller;

import com.example.demo.service.JWTService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    private final JWTService jwtService;

    public TokenController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/generate-token")
    public String generateToken() {
        return jwtService.generateToken();
    }
}