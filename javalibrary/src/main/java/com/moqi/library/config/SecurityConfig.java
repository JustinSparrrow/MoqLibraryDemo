package com.moqi.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/books/approve/**").hasRole("admin") // 仅管理员访问
                        .anyRequest().authenticated() // 其他请求需要认证
                )
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF
                .httpBasic(Customizer.withDefaults()) // 启用 HTTP Basic 认证
                .build();
    }
}
