package com.lakshit.LinkedInProject.UserService.controller;

import com.lakshit.LinkedInProject.UserService.dto.LogInRequestDto;
import com.lakshit.LinkedInProject.UserService.dto.SignUpRequestDto;
import com.lakshit.LinkedInProject.UserService.dto.UserDto;
import com.lakshit.LinkedInProject.UserService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        UserDto userDto = authService.signUp(signUpRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LogInRequestDto logInRequestDto){
        String token = authService.logIn(logInRequestDto);
        return ResponseEntity.ok(token);
    }

}
