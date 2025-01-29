package com.example.questionplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.questionplatform.dto.response.AuthCheckResponse;

@Service
public class AuthCheckService {
    
    private final RestTemplate restTemplate;
    private static final String AUTH_CHECK_URL = "http://localhost:8080/api/auth/check";

    @Autowired
    public AuthCheckService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isTokenValid(String authHeader) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<AuthCheckResponse> response = restTemplate.exchange(
                AUTH_CHECK_URL,
                HttpMethod.GET,
                requestEntity,
                AuthCheckResponse.class
            );

            if (response.getBody() != null) {
                return response.getBody().getMessage().equals("Token is valid");
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
} 
