package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JWTService {
    private static final String SECRET = "Endsemlab32025JwtSecretKeyMustBeAtLeast32BytesLong!";
    private static final long TOKEN_LIFETIME_MILLIS = 60 * 60 * 1000;

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken() {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + TOKEN_LIFETIME_MILLIS);
        return Jwts.builder()
                .subject("sample-user")
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(signingKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(signingKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getExpiration() != null && claims.getExpiration().after(new Date());
        } catch (Exception exception) {
            return false;
        }
    }
}