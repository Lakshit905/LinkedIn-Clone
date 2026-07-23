package com.lakshit.LinkedInProject.UserService.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String name, email, password;
}
