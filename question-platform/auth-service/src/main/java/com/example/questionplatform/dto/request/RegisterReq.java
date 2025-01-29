package com.example.questionplatform.dto.request;

import lombok.Data;

@Data
public class RegisterReq {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String avatar_url;
    private String role;
}
