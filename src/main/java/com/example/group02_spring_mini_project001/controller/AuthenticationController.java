package com.example.group02_spring_mini_project001.controller;

import com.example.group02_spring_mini_project001.exception.EmailException;
import com.example.group02_spring_mini_project001.exception.PasswordException;
import com.example.group02_spring_mini_project001.jwt.JwtTokenUtil;
import com.example.group02_spring_mini_project001.service.AppUserService;
import com.example.group02_spring_mini_project001.userModel.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin()
public class AuthenticationController {
    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationController(AppUserService appUserService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.appUserService = appUserService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/api/v1/register")
    public ResponseEntity<?> register(@RequestBody AppUserRequest appUserRequest){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        boolean valEmail = pattern.matcher(appUserRequest.getEmail()).matches();
        regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}";
        pattern = Pattern.compile(regex);
        boolean valpassword = pattern.matcher(appUserRequest.getPassword()).find();
        if(!valEmail){
            throw new EmailException();
        }
        if(!valpassword){
            throw new PasswordException();
        }
        AppUserDto appUserDto = appUserService.register(appUserRequest);
        ApiResponse<AppUserDto> response = ApiResponse.<AppUserDto>builder()
                .date(LocalDateTime.now())
                .success(true)
                .payload(appUserDto)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping( "/api/v1/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        boolean valEmail = pattern.matcher(authenticationRequest.getEmail()).matches();
        regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}";
        pattern = Pattern.compile(regex);
        boolean valpassword = pattern.matcher(authenticationRequest.getPassword()).find();

        if(!valEmail){
            throw new EmailException();
        }
        if(!valpassword){
            throw new PasswordException();
        }
        final UserDetails userDetails = appUserService
                .loadUserByUsername(authenticationRequest.getEmail());
        try {
            authenticate(authenticationRequest.getEmail(),authenticationRequest.getPassword());
        } catch (Exception e) {
            throw new BadCredentialsException("Account is Not Correct");
        }

        final String token = jwtTokenUtil.generateToken(userDetails);
        AppUser appUser = (AppUser) userDetails;
        ApiResponse<JwtResponse> response = ApiResponse.<JwtResponse>builder()
                .date(LocalDateTime.now())
                .success(true)
                .payload(new JwtResponse(appUser.getUserId(),appUser.getEmail(),token))
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/api/v1/check")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> checkUserLogin(){
        AppUser appUserDto = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApiResponse<Object> response = ApiResponse.<Object>builder()
                .date(LocalDateTime.now())
                .success(true)
                .payload(appUserDto)
                .build();
        return ResponseEntity.ok(response);
    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}

