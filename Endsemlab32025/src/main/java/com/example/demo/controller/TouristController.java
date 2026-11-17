package com.example.demo.controller;

import com.example.demo.entity.Tourist;
import com.example.demo.service.JWTService;
import com.example.demo.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {
    private final TouristService touristService;
    private final JWTService jwtService;

    public TouristController(TouristService touristService, JWTService jwtService) {
        this.touristService = touristService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<Tourist>> getAllTourists(
            @RequestHeader(value = "token", required = false) String token) {
        if (token == null || !jwtService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(touristService.getAllTourists());
    }
}