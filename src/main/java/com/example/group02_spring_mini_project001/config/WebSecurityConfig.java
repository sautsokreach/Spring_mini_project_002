package com.example.group02_spring_mini_project001.config;

import com.example.group02_spring_mini_project001.service.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;
    private final JwtRequestFilter jwtRequestFilter;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, AppUserService appUserService, JwtRequestFilter jwtRequestFilter) {
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //<< implementing this interface

        http.cors().and()
                .csrf().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/bye").permitAll()
                        .requestMatchers("/api/v1/register","/v3/api-docs/**","/api/v1/login",
                                "/swagger-ui/**",
                                "/swagger-ui.html").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic()
                // NO POP-UP LOGIN FORM
                .authenticationEntryPoint((request, response, authException) -> {
//                     response.addHeader("WWW-Authenticate", "Basic realm=\"" + realmName + "\""); <<< (((REMOVED)))
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
                });
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

}
