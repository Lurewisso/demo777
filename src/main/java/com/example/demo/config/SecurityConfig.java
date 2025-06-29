package com.example.demo.config;


import com.example.demo.entity.Credential;
import com.example.demo.repositories.CredentialsRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
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


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/register", "/swagger-ui/", "/v3/api-docs/").permitAll()
//                        .requestMatchers("/user/**").hasRole("USER")
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/register", "/swagger-ui/**", "/v3/api-docs/**","/swagger-resources","/webjars").permitAll()
                    .requestMatchers("/api/users/**").hasRole("USER")
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessHandler((request, response, authentication) -> {
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.getWriter().write("Logout successful");
                    })
            );


    return http.build();
}
}

