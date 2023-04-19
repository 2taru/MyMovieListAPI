package com.taru.my_movie_api.config;

import com.taru.my_movie_api.service.impl.JpaUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    private final JpaUserDetailsServiceImpl jpaUserDetailsService;

    @Autowired
    public SecurityConfig(JpaUserDetailsServiceImpl jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/movie/**", "/api/movieListItem/**")
                .hasRole("USER")
                .requestMatchers("/api/user/create")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(jpaUserDetailsService)
                .httpBasic();

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
