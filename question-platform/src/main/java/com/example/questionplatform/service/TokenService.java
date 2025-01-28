package com.example.questionplatform.service;

import com.example.questionplatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    private static final long TOKEN_EXPIRATION = 24; // 24 hours

    public void saveToken(String token, String userEmail) {
        redisTemplate.opsForValue().set(token, userEmail, TOKEN_EXPIRATION, TimeUnit.HOURS);
    }

    public String getUserEmailFromToken(String token) {
        return redisTemplate.opsForValue().get(token);
    }

    public void removeToken(String token) {
        redisTemplate.delete(token);
    }

    public boolean isTokenValid(String token) {
        return redisTemplate.hasKey(token);
    }
} 