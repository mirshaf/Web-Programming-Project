package com.example.questionplatform.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.questionplatform.service.AuthCheckService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthCheckService authCheckService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Skip OPTIONS requests (CORS preflight)
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\": \"Authorization header is missing or invalid\"}");
            return false;
        }

        if (!authCheckService.isTokenValid(authHeader)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\": \"Unauthorized\"}");
            return false;
        }

        return true;
    }
} 