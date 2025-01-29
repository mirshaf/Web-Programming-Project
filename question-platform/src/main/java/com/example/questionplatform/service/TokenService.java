package com.example.questionplatform.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    private static final long TOKEN_EXPIRATION = 24; // 24 hours
    private static final String TOKEN_PREFIX = "token:";
    private static final String USER_TOKENS_PREFIX = "user_tokens:";

    public void saveToken(String token, String userEmail) {
        String tokenKey = TOKEN_PREFIX + token;
        String userTokensKey = USER_TOKENS_PREFIX + userEmail;
        
        // Save token -> email mapping
        redisTemplate.opsForValue().set(tokenKey, userEmail, TOKEN_EXPIRATION, TimeUnit.HOURS);
        
        // Add token to user's set of tokens
        redisTemplate.opsForSet().add(userTokensKey, token);
        redisTemplate.expire(userTokensKey, TOKEN_EXPIRATION, TimeUnit.HOURS);
    }

    public String getUserEmailFromToken(String token) {
        return redisTemplate.opsForValue().get(TOKEN_PREFIX + token);
    }

    public void removeToken(String token) {
        String userEmail = getUserEmailFromToken(token);
        if (userEmail != null) {
            String userTokensKey = USER_TOKENS_PREFIX + userEmail;
            redisTemplate.opsForSet().remove(userTokensKey, token);
            redisTemplate.delete(TOKEN_PREFIX + token);
        }
    }

    public boolean isTokenValid(String token) {
        return redisTemplate.hasKey(TOKEN_PREFIX + token);
    }
} 