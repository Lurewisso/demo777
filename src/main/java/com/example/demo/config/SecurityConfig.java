package com.example.demo.config;


import com.example.demo.entity.Credential;
import com.example.demo.repositories.CredentialsRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils();
    }

    @Bean
    UserDetailsService userDetailsService(CredentialsRepository credentialsRepository){
        return username -> {
            Credential credential = credentialsRepository.findByUserName(username)
                    .orElseThrow(()->new UsernameNotFoundException(username));
            return User.builder()
                    .username(username)
                    .password(credential.getPassword())
                    .roles(credential.getRole().getName().replace("ROLE_",""))
                    .build();
        };
    }

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtils jwtUtils, CredentialsRepository credentialsRepository)
        throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils, userDetailsService(credentialsRepository));

    http.csrf(AbstractHttpConfigurer::disable)

            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
                    .requestMatchers("/api/auth/register", "/swagger-ui/**", "/v3/api-docs/**","/swagger-resources","/webjars").permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }
}

