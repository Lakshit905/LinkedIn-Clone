package com.lakshit.LinkedInProject.UserService.service;

import com.lakshit.LinkedInProject.UserService.dto.LogInRequestDto;
import com.lakshit.LinkedInProject.UserService.dto.SignUpRequestDto;
import com.lakshit.LinkedInProject.UserService.dto.UserDto;
import com.lakshit.LinkedInProject.UserService.entity.User;
import com.lakshit.LinkedInProject.UserService.exception.BadRequestException;
import com.lakshit.LinkedInProject.UserService.repository.UserRepository;
import com.lakshit.LinkedInProject.UserService.utils.Bcrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        log.info("signup the user with email: {}",signUpRequestDto.getEmail());
        Boolean isUserAlreadyExists = userRepository.existsByEmail(signUpRequestDto.getEmail());
        if(isUserAlreadyExists){
            throw new BadRequestException("User Already exists");
        }
        User user = modelMapper.map(signUpRequestDto,User.class);
        user.setPassword(Bcrypt.hash(signUpRequestDto.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public String logIn(LogInRequestDto logInRequestDto) {
        log.info("login user with email id: {}",logInRequestDto.getEmail());

        User user = userRepository.findByEmail(logInRequestDto.getEmail())
                .orElseThrow(() -> new BadRequestException("Incorrect email or password"));

        Boolean isPasswordCorrect = Bcrypt.match(logInRequestDto.getPassword(),user.getPassword());

        if(!isPasswordCorrect){
           throw new BadRequestException("Incorrect email or password");
        }

        return jwtService.generateAccessToken(user);
    }
}
