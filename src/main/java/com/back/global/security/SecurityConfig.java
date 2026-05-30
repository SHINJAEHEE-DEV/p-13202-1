package com.back.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity//
public class SecurityConfig {
    //SecurityConfig는  스프링 시큐리티의 보안 설정을 담당하는 클래스입니다.
    // 이 클래스는 스프링 시큐리티를 활성화하고, HTTP 요청에 대한 보안 규칙을 정의합니다. 또한, 인증 관리자(AuthenticationManager)를 빈으로 등록하여 인증 프로세스를 처리할 수 있도록 합니다.
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(("/**")).permitAll())
                .formLogin((formLogin) -> formLogin
                        .loginPage("/members/login")
                        .loginProcessingUrl("/members/login")
                        .defaultSuccessUrl("/"))
                .logout((logout) -> logout
                        .logoutUrl(("/members/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
        ;
        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}