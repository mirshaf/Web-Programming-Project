package com.example.questionplatform.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "asdfghjkasdfghjkasdfghjkasdfghjk"; // Use a strong secret key
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    private static Key getSigningKey() {

        return new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());

    }

    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject(); // Returns the email
        } catch (Exception e) {
            return null; // Invalid token
        }
    }
}
