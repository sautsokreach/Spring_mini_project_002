package com.example.group02_spring_mini_project001.service;

import com.example.group02_spring_mini_project001.repository.AppUserRepository;
import com.example.group02_spring_mini_project001.userModel.AppUser;
import com.example.group02_spring_mini_project001.userModel.AppUserDto;
import com.example.group02_spring_mini_project001.userModel.AppUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final ModelMapper mapper = new ModelMapper();

    public AppUserService(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(username);
    }
    public UserDetails checkUser(String username){
        return appUserRepository.findByEmail(username);
    }

    public AppUserDto register(AppUserRequest appUserRequest) {
        String pass = passwordEncoder.encode(appUserRequest.getPassword());
        appUserRequest.setPassword(pass);
        AppUser appUser = appUserRepository.insertUser(appUserRequest);
        return mapper.map(appUser, AppUserDto.class);
    }
}
